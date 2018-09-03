/**
 * 
 */
package dev.galaxyForcaster.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dev.galaxyForcaster.entities.*;

/**
 * @author richard
 *
 */
public class PronosticoDao {

	/**
	 * 
	 */
	public PronosticoDao() {
		// TODO Auto-generated constructor stub
	}

	public static final String INSERT = "INSERT INTO Pronostico (IdentificadorPeriodo,perimetroArea,condicionClimatica) VALUES (?,?,?)";

	public static final String UPDATE = "UPDATE Pronostico  set perimetroArea =?,condicionClimatica =? where IdentificadorPeriodo =? ";

	public static void insertar(Pronostico p) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBHelper.getConexion();

			pstmt = conn.prepareStatement(INSERT);

			pstmt.setLong(1, p.getIdentificadorPeriodo());
			pstmt.setDouble(2, p.getPerimetroArea());
			pstmt.setString(3, p.getCondicionClimatica());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}

	}

	public static void update(Pronostico p) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBHelper.getConexion();

			pstmt = conn.prepareStatement(INSERT);

			pstmt.setDouble(1, p.getPerimetroArea());
			pstmt.setString(2, p.getCondicionClimatica());

			pstmt.setLong(3, p.getIdentificadorPeriodo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}

	}

}
