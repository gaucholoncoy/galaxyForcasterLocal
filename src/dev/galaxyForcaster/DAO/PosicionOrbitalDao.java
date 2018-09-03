/**
 * 
 */
package dev.galaxyForcaster.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dev.galaxyForcaster.entities.PosicionOrbital;

/**
 * @author richard
 *
 */
public class PosicionOrbitalDao {

	/**
	 * 
	 */
	public PosicionOrbitalDao() {
		// TODO Auto-generated constructor stub
	}

	public static final String INSERT = "INSERT INTO PosicionOrbital (IdentificadorPeriodo,nombrePlaneta,posicionAngular,posicionCartesaianaX,posicionCartesaianaY) "
			+ "VALUES (?,?,?,?,?)";
	public static final String UPDATE = "UPDATE PosicionOrbital  set posicionAngular =?,posicionCartesaianaX =?,posicionCartesaianaY =? where IdentificadorPeriodo =? and nombrePlaneta=?";

	public static void insertar(PosicionOrbital p) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBHelper.getConexion();

			pstmt = conn.prepareStatement(INSERT);

			pstmt.setLong(1, p.getIdentificadorPeriodo());
			pstmt.setString(2, p.getNombrePlaneta());
			pstmt.setLong(3, p.getPosicionAngular());
			pstmt.setDouble(4, p.getCoordenadas().getX());
			pstmt.setDouble(5, p.getCoordenadas().getY());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}

	}

	public static void update(PosicionOrbital p) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBHelper.getConexion();

			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setLong(1, p.getPosicionAngular());
			pstmt.setDouble(2, p.getCoordenadas().getX());
			pstmt.setDouble(3, p.getCoordenadas().getY());
			pstmt.setLong(4, p.getIdentificadorPeriodo());
			pstmt.setString(5, p.getNombrePlaneta());			

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}

	}

}
