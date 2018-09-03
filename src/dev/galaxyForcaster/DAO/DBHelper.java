package dev.galaxyForcaster.DAO;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import dev.galaxyForcaster.service.Constantes;

public class DBHelper {

	public static Connection getConexion() {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(Constantes.URL);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;

	}

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

	public static void executeSQL(String sql) {

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(Constantes.URL);
			stmt = conn.createStatement();

			stmt.execute(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, stmt);
		}

	}

	/**
	 * Connect to a sample database
	 *
	 * @param fileName the database file name
	 */
	public static void createNewDatabase() {

		try (Connection conn = DriverManager.getConnection(Constantes.URL)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void createTables() {
		// SQLite connection string

		// SQL statement for creating a new table

		try (Connection conn = DriverManager.getConnection(Constantes.URL); Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(Constantes.SQL_Creacion_Planeta);
			System.out.println("Creacion de table " + Constantes.SQL_Creacion_Planeta);
			stmt.execute(Constantes.SQL_Creacion_PosicionOrbital);
			System.out.println("Creacion de table " + Constantes.SQL_Creacion_PosicionOrbital);
			stmt.execute(Constantes.SQL_Creacion_Pronostico);
			System.out.println("Creacion de table " + Constantes.SQL_Creacion_Pronostico);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void dropTables() {
		// SQLite connection string

		// SQL statement for creating a new table

		try (Connection conn = DriverManager.getConnection(Constantes.URL); Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(Constantes.SQL_Drop_Planeta);
			System.out.println("Drop de table " + Constantes.SQL_Drop_Planeta);
			stmt.execute(Constantes.SQL_Drop_PosicionOrbital);
			System.out.println("Drop de table " + Constantes.SQL_Drop_PosicionOrbital);
			stmt.execute(Constantes.SQL_Drop_Pronostico);
			System.out.println("Drop de table " + Constantes.SQL_Drop_Pronostico);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
