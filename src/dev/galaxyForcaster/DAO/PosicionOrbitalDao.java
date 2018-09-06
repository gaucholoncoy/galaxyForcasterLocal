/**
 * 
 */
package dev.galaxyForcaster.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.galaxyForcaster.entities.PosicionOrbital;

/**
 * Clase de acceso a DB para ser usado por la clase PosicionOrbital, la idea es evolucionar hacia una persistencia 
 * mas amigable que esta
 * @author richard
 *
 */
public class PosicionOrbitalDao {

	/**
	 * 
	 */

	final static Logger log = LoggerFactory.getLogger(PosicionOrbitalDao.class);

	public PosicionOrbitalDao() {
		// TODO Auto-generated constructor stub
	}

	public static final String INSERT = "INSERT INTO PosicionOrbital (IdentificadorPeriodo,nombrePlaneta,posicionAngular,posicionCartesaianaX,posicionCartesaianaY) "
			+ "VALUES (?,?,?,?,?)";
	public static final String UPDATE = "UPDATE PosicionOrbital  set posicionAngular =?,posicionCartesaianaX =?,posicionCartesaianaY =? where IdentificadorPeriodo =? and nombrePlaneta=?";

	/**
	 * Metodo de Insertar una posicionorbital de un planeta
	 *
	 * @author richard
	 **@param PosicionOrbital p 
	 */
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
			log.debug(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}

	}

	/**
	 * Metodo de Update una posicionorbital de un planeta
	 *
	 * @author richard
	 **@param PosicionOrbital p 
	 */
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
			log.debug(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}

	}

}
