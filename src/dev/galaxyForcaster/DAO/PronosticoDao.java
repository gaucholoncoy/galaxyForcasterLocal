/**
 * 
 */
package dev.galaxyForcaster.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import dev.galaxyForcaster.entities.Pronostico;

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
	public static final String QUERY_list_clima = "select * from pronostico p where p.IdentificadorPeriodo = ?";
	public static final String QUERY_list_estadisticas_clima = "select p.condicionClimatica as periodo ,  count(*) as cantidad from pronostico p group by p.condicionClimatica";

	public static final String QUERY_list_estadisticas_lluvia = "select p.IdentificadorPeriodo, p.perimetroArea from pronostico p where p.perimetroArea in (select max(a.perimetroArea) from pronostico a where a.condicionClimatica='LLUVIA')";

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
			System.out.println(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}
		return pro;

	}

	public static LinkedHashMap<String, String> consultarEstadisticasPeriodos() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		Pronostico pro = null;
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
			System.out.println(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}
		return estadisticasPeriodos;

	}
	
	public static LinkedHashMap<String, String> consultarestadisticasLLuvia() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		Pronostico pro = null;
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
			System.out.println(e.getMessage());
		} finally {
			DBHelper.releaseConexion(conn, pstmt);
		}
		return estadisticasLLuvia;

	}

}
