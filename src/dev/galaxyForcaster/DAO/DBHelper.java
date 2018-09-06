package dev.galaxyForcaster.DAO;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.JDBC;

import dev.galaxyForcaster.service.Constantes;

/**
 * Clase para acceder a la DB y  ejecutar los comando SQL tanto DDL como DML
 * @author richard
 *
 */
public class DBHelper {

	final static Logger log = LoggerFactory.getLogger(DBHelper.class);

// en Apache  da erro de carga del jdbc y buscando encontre esta WA  que funciona, 
	static {
		try {
			DriverManager.registerDriver(new JDBC());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para dar una conexion a alguna clase DAO que la utilizar
	 * @author richard
	 *
	 */
	public static Connection getConexion() {

		Connection conn = null;

		try {

			conn = DriverManager.getConnection(Constantes.URL);

		} catch (SQLException e) {
			log.debug(e.getMessage());
		}
		return conn;

	}

	/**
	 * Metodo para liberar y no tener MLeaks en el cierre de stmt y conn a la DB
	 * @author richard
	 *
	 */
	public static void releaseConexion(Connection conn, Statement stmt) {

		try {
			if (!(stmt == null))
				stmt.close();

			if (!(conn == null))
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Metodo para ejecutar sentencias que no requieren informacion de respuesta
	 * 
	 * @author richard
	 *
	 */
	public static void executeSQL(String sql) {

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(Constantes.URL);
			stmt = conn.createStatement();

			stmt.execute(sql);

		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, stmt);
		}

	}

	/**
	 * Metodo para crear la db cada vez que se invoca l ENDPOINT galaxyForcaster/rest/clima/inicio
	 *
	 *  * @author richard
	 */
	public static void createNewDatabase() {

		try (Connection conn = DriverManager.getConnection(Constantes.URL)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				log.debug("The driver name is " + meta.getDriverName());
				log.debug("A new database has been created.");
			}

		} catch (SQLException e) {
			log.debug(e.getMessage());
		}
	}
	
	/**
	 * Metodo para crear tablas 
	 *
	 *  * @author richard
	 */

	public static void createTables() {
		// SQLite connection string

		// SQL statement for creating a new table

		try (Connection conn = DriverManager.getConnection(Constantes.URL); Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(Constantes.SQL_Creacion_Planeta);
			log.debug("Creacion de table " + Constantes.SQL_Creacion_Planeta);
			stmt.execute(Constantes.SQL_Creacion_PosicionOrbital);
			log.debug("Creacion de table " + Constantes.SQL_Creacion_PosicionOrbital);
			stmt.execute(Constantes.SQL_Creacion_Pronostico);
			log.debug("Creacion de table " + Constantes.SQL_Creacion_Pronostico);
		} catch (SQLException e) {
			log.debug(e.getMessage());
		}
	}
	
	/**
	 * Metodo para dropear las tablas cuando se recrea la db luego de invocar al ENDPOINT galaxyForcaster/rest/clima/inicio
	 *
	 *  * @author richard
	 */

	public static void dropTables() {
		// SQLite connection string

		// SQL statement for creating a new table

		try (Connection conn = DriverManager.getConnection(Constantes.URL); Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(Constantes.SQL_Drop_Planeta);
			log.debug("Drop de table " + Constantes.SQL_Drop_Planeta);
			stmt.execute(Constantes.SQL_Drop_PosicionOrbital);
			log.debug("Drop de table " + Constantes.SQL_Drop_PosicionOrbital);
			stmt.execute(Constantes.SQL_Drop_Pronostico);
			log.debug("Drop de table " + Constantes.SQL_Drop_Pronostico);
		} catch (SQLException e) {
			log.debug(e.getMessage());
		}
	}
}
