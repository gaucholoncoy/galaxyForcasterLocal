/**
 * 
 */
package dev.galaxyForcaster.entities;

/**
 * Clase utilitaria para devolver las estadisticas al responsable del servicio
 * 
 * @author richard
 *
 */
public class Estadistica {

	/**
	 * 
	 */
	public Estadistica() {
		// TODO Auto-generated constructor stub
	}

	public Estadistica(String descripcion, String periodo, String cantidad) {
		this.descripcion = descripcion;
		this.periodo = periodo;
		this.cantidad = cantidad;
	}

	private String descripcion; // describe que metrica tipo de metrica es podria ser totales o valores maximo 
	private String periodo;   // dia 
	private String cantidad; 

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

}
