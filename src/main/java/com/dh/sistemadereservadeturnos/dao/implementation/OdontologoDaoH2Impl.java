package com.dh.sistemadereservadeturnos.dao.implementation;

import com.dh.sistemadereservadeturnos.dao.H2DB;
import com.dh.sistemadereservadeturnos.dao.IDao;
import com.dh.sistemadereservadeturnos.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2Impl implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2Impl.class);


    private final static String SQL_INSERT_ODONTOLOGOS = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) VALUES (?,?,?)";
    private final static String SQL_SELECT_ODONTOLOGOS = "SELECT * FROM ODONTOLOGOS";
    private final static String SQL_SELECT_ODONTOLOGOS_BY_ID = "SELECT * FROM ODONTOLOGOS WHERE id = ?";
    private final static String SQL_DELETE_ODONTOLOGOS = "DELETE FROM ODONTOLOGOS WHERE ID = ?";
    private final static String SQL_UPDATE_ODONTOLOGOS = "UPDATE ODONTOLOGOS SET NOMBRE = ?, APELLIDO = ?, MATRICULA = ? WHERE ID = ?";


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection conn = null;
        Odontologo odontologoCreado = null;

        try {
            conn = H2DB.getConnection();

            PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT_ODONTOLOGOS, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getNombre());
            psInsert.setString(2, odontologo.getApellido());
            psInsert.setInt(3, odontologo.getMatricula());

            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                odontologoCreado = new Odontologo(rs.getInt(1), odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());
            }

            LOGGER.info("Se creo un odontologo");
        } catch (Exception e) {
            LOGGER.error("Error al crear nuevo odontólogo: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("Error al cerrar la conexion: " + e.getMessage());
                }

            }


        }
        return odontologoCreado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection conn = null;
        List<Odontologo> odontologos = new ArrayList();

        try {
            conn = H2DB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_ODONTOLOGOS);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                odontologos.add(odontologo);

                LOGGER.info("Se buscaron todos los odontologos");
            }
        } catch (Exception e) {
            LOGGER.error("Error al buscar todos los odontologos: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("Error al cerrar la conexion " + e.getMessage());
                }

            }
        }

        return odontologos;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        Connection conn = null;
        Odontologo odontologoBuscado = null;

        try {
            conn = H2DB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_ODONTOLOGOS_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                odontologoBuscado = new Odontologo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
            }

            LOGGER.info("Se busco un odontologo ");


        } catch (Exception e) {
            LOGGER.error("Error al buscar ondontologo: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("Error al cerrar la conexion: " + e.getMessage());
                }

            }

        }
        return odontologoBuscado;

    }

    @Override
    public void eliminar(Integer id) {
        Connection conn = null;
        try {
            conn = H2DB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_ODONTOLOGOS);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            LOGGER.info("Se elimino un odontologo ");


        } catch (Exception e) {
            LOGGER.error("Error al eliminar odontólogo: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("Error al cerrar la conexion: " + e.getMessage());
                }

            }
        }

    }

    @Override
    public void actualizar(Odontologo odontologo) {
        Connection conn = null;

        try {

            conn = H2DB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_ODONTOLOGOS);

            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getMatricula());
            preparedStatement.setInt(4, odontologo.getId());

            preparedStatement.execute();
            LOGGER.info("Se actualizo un Odontologo " + odontologo);

        } catch (Exception e) {
            LOGGER.error("Error al actualizar Odontologo " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("Error al cerrar la conexion: " + e.getMessage());
                }

            }
        }
    }
}
