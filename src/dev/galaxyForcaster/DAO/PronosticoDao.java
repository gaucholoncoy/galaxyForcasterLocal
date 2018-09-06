/**
 * 
 */
package dev.galaxyForcaster.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.galaxyForcaster.entities.Pronostico;

/**
 * Clase de acceso a DB para ser usado por la clase Pronostico, la idea es evolucionar hacia una persistencia 
 * mas amigable que esta
 * @author richard
 *
 */
public class PronosticoDao {

	/**
	 * 
	 */

	final static Logger log = LoggerFactory.getLogger(PosicionOrbitalDao.class);

	public PronosticoDao() {
		// TODO Auto-generated constructor stub
	}

	public static final String INSERT = "INSERT INTO Pronostico (IdentificadorPeriodo,perimetroArea,condicionClimatica) VALUES (?,?,?)";

	public static final String UPDATE = "UPDATE Pronostico  set perimetroArea =?,condicionClimatica =? where IdentificadorPeriodo =? ";
	public static final String QUERY_list_clima = "select * from pronostico p where p.IdentificadorPeriodo = ?";
	public static final String QUERY_list_estadisticas_clima = "select p.condicionClimatica as periodo ,  count(*) as cantidad from pronostico p group by p.condicionClimatica";

	public static final String QUERY_list_estadisticas_lluvia = "select p.IdentificadorPeriodo, p.perimetroArea from pronostico p where p.perimetroArea in (select max(a.perimetroArea) from pronostico a where a.condicionClimatica='LLUVIA')";

	/**
	 * Metodo de Insertar un Pronostico 
	 * 
	 * @author richard
	 **@param Pronostico p 
	 */
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
			log.debug(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}

	}

	/**
	 * Metodo de update un Pronostico 
	 * 
	 * @author richard
	 **@param Pronostico p 
	 */
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
			log.debug(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}

	}
	
	/**
	 * Metodo de consulta de pronistico para un dia en particual  
	 * 
	 * @author richard
	 **@param int dia, devuelve un objeto pronostico 
	 */

	public static Pronostico consultarPronostico(int dia) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		Pronostico pro = null;

		try {
			conn = DBHelper.getConexion();

			pstmt = conn.prepareStatement(QUERY_list_clima);

			pstmt.setInt(1, dia);

			ResultSet rs = pstmt.executeQuery();

			// loop through the result set

			while (rs.next()) {
				pro = new Pronostico();
				pro.setCondicionClimatica(rs.getString("condicionClimatica"));
				pro.setPerimetroArea(rs.getDouble("cantidad"));
			}
			rs.close();
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}
		return pro;

	}

	/**
	 * Metodo de consulta de las estadisticas de los pronosticos ,  agrupados por tipo de clima y cantidas de cada tipo
	 * 
	 * @author richard
	 **@param int dia
	 *devuelve una lista de metricas( periodo, cantidad)
	 */
	public static LinkedHashMap<String, String> consultarEstadisticasPeriodos() {

		Connection conn = null;
		PreparedStatement pstmt = null;

		LinkedHashMap<String, String> estadisticasPeriodos = new LinkedHashMap<String, String>();
		try {
			conn = DBHelper.getConexion();

			pstmt = conn.prepareStatement(QUERY_list_estadisticas_clima);

			ResultSet rs = pstmt.executeQuery();

			// loop through the result set

			while (rs.next()) {

				estadisticasPeriodos.put(rs.getString("periodo"), rs.getString("cantidad"));
			}
			rs.close();
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}
		return estadisticasPeriodos;

	}

	
	/**
	 * Metodo de consulta de las estadisticas de los lluvia maximas y en que dia fue.
	 * 
	 * @author richard
	 **@param int dia
	 *devuelve una lista de metricas( IdentificadorPeriodo, perimetroArea)
	 *									dia, cantidad de lluvia de ese ma
	 */
	
	public static LinkedHashMap<String, String> consultarestadisticasLLuvia() {

		Connection conn = null;
		PreparedStatement pstmt = null;

		LinkedHashMap<String, String> estadisticasLLuvia = new LinkedHashMap<String, String>();
		try {
			conn = DBHelper.getConexion();

			pstmt = conn.prepareStatement(QUERY_list_estadisticas_lluvia);

			ResultSet rs = pstmt.executeQuery();

			// loop through the result set

			while (rs.next()) {

				estadisticasLLuvia.put(rs.getString("IdentificadorPeriodo"), rs.getString("perimetroArea"));
			}
			rs.close();
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}
		return estadisticasLLuvia;

	}

}
