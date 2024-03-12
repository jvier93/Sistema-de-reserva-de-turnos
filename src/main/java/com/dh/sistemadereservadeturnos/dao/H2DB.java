package com.dh.sistemadereservadeturnos.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class H2DB {

    private static final Logger LOGGER = Logger.getLogger(H2DB.class);


    private final static String H2DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String H2DB_URL = "jdbc:h2:./Databases/Clinica";
    private final static String H2DB_USER = "sa";
    private final static String H2DB_PASSWORD = "sa";
    private static final String SQL_DROP_CREATE_ODONTOLOGOS = "DROP TABLE IF EXISTS " +
            "ODONTOLOGOS; CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " NOMBRE VARCHAR(100) NOT NULL," +
            " APELLIDO VARCHAR(100)  NOT NULL," +
            " MATRICULA VARCHAR(100) NOT NULL)";

    private static final String SQL_DROP_CREATE_DOMICILIOS = "DROP TABLE IF EXISTS " +
            "DOMICILIOS; CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " CALLE VARCHAR(100) NOT NULL," +
            " NUMERO INT NOT NULL," +
            " LOCALIDAD VARCHAR(100) NOT NULL," +
            " PROVINCIA VARCHAR(100) NOT NULL" +
            ")";

    private static final String SQL_DROP_CREATE_PACIENTES = "DROP TABLE IF EXISTS " +
            "PACIENTES; CREATE TABLE PACIENTES (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " NOMBRE VARCHAR(100) NOT NULL," +
            " APELLIDO VARCHAR(100)  NOT NULL," +
            " DNI VARCHAR(100) NOT NULL ," +
            " FECHA_INGRESO DATE NOT NULL," +
            " DOMICILIO_ID INT NOT NULL)";


    public static Connection getConnection() throws Exception {
        Class.forName(H2DB_JDBC_DRIVER);
        return DriverManager.getConnection(H2DB_URL,
                H2DB_USER, H2DB_PASSWORD);
    }

    public static void crearTablas() {
        Connection connection = null;

        try {

            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_DROP_CREATE_ODONTOLOGOS);
            statement.execute(SQL_DROP_CREATE_DOMICILIOS);
            statement.execute(SQL_DROP_CREATE_PACIENTES);

            LOGGER.info("Se creo la tabla odontologos");
            LOGGER.info("Se creo la tabla domicilios");
            LOGGER.info("Se creo la tabla pacientes");

        } catch (Exception e) {
            LOGGER.error("Error al crear la tabla odontologos: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexion: " + e.getMessage());

            }
        }

    }


}
