package cesur.examen.core.common;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: José Miguel Ruiz Guevara
 * Fecha: 11/12/2023
 *
 * No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 * En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 * o para seguir la traza de ejecución.
 */
@Log
public class JDBCUtils {

    /**
     * Connection is stored as a static final object accessible by all other classes.
     * Connection data is retrieved from db.properties file located in resource folder.
     *
     * Remember to open an InputStream to a file located in resource folder using
     * JDBCUtils.class.getClassLoader().getResourceAsStream()
     */
    private static final Connection conn;

    static{

        try {
            // Carga el driver JDBC (dependiendo de tu base de datos)
            Class.forName("com.mysql.jdbc.Driver");

            // Información de conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/examenad";
            String username = "root";
            String password = "";

            // Establece la conexión con la base de datos
            conn = DriverManager.getConnection(url, username, password);

            if (conn != null) {
                log.info("Succesfully connected!");
            } else {
                log.info("JDBCUtils Not implemented yet!");
            }

        } catch (ClassNotFoundException e) {
            log.severe("JDBC Driver not found: " + e.getMessage());
            throw new RuntimeException("JDBC Driver not found", e);
        } catch (SQLException e) {
            log.severe("Error connecting to database: " + e.getMessage());
            throw new RuntimeException("Error connecting to database", e);
        } catch (Exception e) {
            log.severe("General error: " + e.getMessage());
            throw new RuntimeException("Error during database connection initialization", e);
        }
    }


    public static Connection getConn() {
        return conn;
    }

    /**
     * Conversion utility from util.Date to sql.Date
     * Remember that sql.Date should be used in Jdbc queries but
     * class java.util.Date cannot be cast to class java.sql.Date
     * We can do the cast (util.Date) sql.Date safely, but not backwards.
     *
     * @param d date in util.Date format
     * @return the same date in sql.Date date
     */
    public static java.sql.Date dateUtilToSQL( java.util.Date d){
        return new java.sql.Date(d.getTime());
    }

}
