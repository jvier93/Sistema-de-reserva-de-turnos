package com.dh.sistemadereservadeturnos.dao.implementation;

import com.dh.sistemadereservadeturnos.dao.H2DB;
import com.dh.sistemadereservadeturnos.dao.IDao;
import com.dh.sistemadereservadeturnos.model.Domicilio;
import com.dh.sistemadereservadeturnos.model.Odontologo;
import com.dh.sistemadereservadeturnos.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class PacienteDaoH2Impl implements IDao<Paciente> {
    private final Logger LOGGER = Logger.getLogger(PacienteDaoH2Impl.class);


    private static final String SQL_INSERT_PACIENTES = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, DOMICILIO_ID) VALUES (?,?,?,?,?)";
    private final static String SQL_SELECT_PACIENTES_BY_ID = "SELECT * FROM PACIENTES WHERE id = ?";

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection conn = null;
        Paciente pacienteCreado = null;

        try {
            conn = H2DB.getConnection();

            DomicilioDaoH2Impl domicilioDaoH2 = new DomicilioDaoH2Impl();
            Domicilio domicilio = domicilioDaoH2.guardar(paciente.getDomicilio());

            PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT_PACIENTES, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getDni());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5, domicilio.getId());


            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                pacienteCreado = new Paciente(rs.getInt(1), paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaIngreso(), domicilio);
            }

            LOGGER.info("Se creo un paciente");
        } catch (Exception e) {
            LOGGER.error("Error al crear nuevo paciente: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("Error al cerrar la conexion: " + e.getMessage());
                }

            }


        }
        return pacienteCreado;


    }

    @Override
    public List<Paciente> listarTodos() {
        return null;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        Connection conn = null;
        Paciente pacienteBuscado = null;

        try {
            conn = H2DB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_PACIENTES_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                IDao<Domicilio> domicilioDaoH2Impl = new DomicilioDaoH2Impl();
                Domicilio domicilio = domicilioDaoH2Impl.buscarPorId(resultSet.getInt(6));

                pacienteBuscado = new Paciente(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5).toLocalDate(), domicilio);
            }

            LOGGER.info("Se busco un paciente ");


        } catch (Exception e) {
            LOGGER.error("Error al buscar paciente: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("Error al cerrar la conexion: " + e.getMessage());
                }

            }

        }
        return pacienteBuscado;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Paciente paciente) {

    }
}
