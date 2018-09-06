/**
 * 
 */
package dev.galaxyForcaster.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.galaxyForcaster.entities.Planeta;

/**
 * Clase de acceso a DB para ser usado por la clase Planeta, la idea es evolucionar hacia una persistencia 
 * mas amigable que esta
 * @author richard
 *
 */
public class PlanetaDao {

	final static Logger log = LoggerFactory.getLogger(PlanetaDao.class);

	/**
	 * 
	 */
	public PlanetaDao() {
		// TODO Auto-generated constructor stub
	}

	public static final String INSERT = "INSERT INTO Planeta (nombre,velocidadAngular,distanciaSol,sentidoGiroOrbita,posicionAngularInicial,posicionAngularActual) "
			+ "VALUES (?,?,?,?,?,?)";

	public static final String UPDATE = "UPDATE Planeta set velocidadAngular = ? , distanciaSol =? ,sentidoGiroOrbita =? , posicionAngularInicial= ?, posicionAngularActual=? WHERE nombre=? ";

	/**
	 * Metodo de Insertar un planeta (clase Planeta)
	 *
	 * @author richard
	 *@param planeta 
	 */
	public static void insertar(Planeta planeta) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBHelper.getConexion();

			pstmt = conn.prepareStatement(INSERT);

			pstmt.setString(1, planeta.getNombre());
			pstmt.setInt(2, planeta.getVelocidadAngular());
			pstmt.setInt(3, planeta.getDistanciaSol());
			pstmt.setString(4, planeta.getSentidoGiroOrbita());
			pstmt.setLong(5, planeta.getPosicionAngularInicial());
			pstmt.setLong(6, planeta.getPosicionAngularActual());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}

	}
	
	/**
	 * Metodo de Update de  un planeta (clase Planeta)
	 *
	 * @author richard
	 **@param planeta 
	 */

	public static void update(Planeta planeta) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBHelper.getConexion();

			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setInt(1, planeta.getVelocidadAngular());
			pstmt.setInt(2, planeta.getDistanciaSol());
			pstmt.setString(3, planeta.getSentidoGiroOrbita());
			pstmt.setLong(4, planeta.getPosicionAngularInicial());
			pstmt.setLong(5, planeta.getPosicionAngularActual());
			pstmt.setString(6, planeta.getNombre());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}

	}

}
