package com.dh.sistemadereservadeturnos.dao.implementation;

import com.dh.sistemadereservadeturnos.dao.H2DB;
import com.dh.sistemadereservadeturnos.dao.IDao;
import com.dh.sistemadereservadeturnos.model.Domicilio;
import com.dh.sistemadereservadeturnos.model.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class DomicilioDaoH2Impl implements IDao<Domicilio> {

    private final Logger LOGGER = Logger.getLogger(DomicilioDaoH2Impl.class);

    private static final String SQL_INSERT_DOMICILIO = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private final static String SQL_SELECT_DOMICILIOS_BY_ID = "SELECT * FROM DOMICILIOS WHERE id = ?";

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection conn = null;
        Domicilio domicilioCreado = null;

        try {
            conn = H2DB.getConnection();

            PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT_DOMICILIO, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2, domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());


            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                domicilioCreado = new Domicilio(rs.getInt(1), domicilio.getCalle(), domicilio.getNumero(), domicilio.getLocalidad(), domicilio.getProvincia());
            }

            LOGGER.info("Se creo un domicilio");
        } catch (Exception e) {
            LOGGER.error("Error al crear nuevo domicilio: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("Error al cerrar la conexion: " + e.getMessage());
                }

            }


        }
        return domicilioCreado;
    }

    @Override
    public List<Domicilio> listarTodos() {
        return null;
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        Connection conn = null;
        Domicilio domicilioBuscado = null;

        try {
            conn = H2DB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_DOMICILIOS_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                domicilioBuscado = new Domicilio(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5));
            }

            LOGGER.info("Se busco un domicilio ");

        } catch (Exception e) {
            LOGGER.error("Error al buscar domicilio: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("Error al cerrar la conexion: " + e.getMessage());
                }

            }
        }

        return domicilioBuscado;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Domicilio domicilio) {

    }
}
