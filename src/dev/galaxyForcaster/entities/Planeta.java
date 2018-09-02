/**
 * 
 */
package dev.galaxyForcaster.entities;

/**
 * @author richard
 *
 */
public class Planeta {

	/**
	 * 
	 */

	private String nombre;
	private int velocidadAngular = 0;
	private int distanciaSol = 0;
	private String sentidoGiroOrbita;
	private long posicionAngularInicial = 0;
	private long posicionAngularActual = 0;

	public Planeta() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the velocidadAngular
	 */
	public int getVelocidadAngular() {
		return velocidadAngular;
	}

	/**
	 * @param velocidadAngular the velocidadAngular to set
	 */
	public void setVelocidadAngular(int velocidadAngular) {
		this.velocidadAngular = velocidadAngular;
	}

	/**
	 * @return the distanciaSol
	 */
	public int getDistanciaSol() {
		return distanciaSol;
	}

	/**
	 * @param distanciaSol the distanciaSol to set
	 */
	public void setDistanciaSol(int distanciaSol) {
		this.distanciaSol = distanciaSol;
	}

	/**
	 * @return the sentidoGiroOrbita
	 */
	public String getSentidoGiroOrbita() {
		return sentidoGiroOrbita;
	}

	/**
	 * @param sentidoGiroOrbita the sentidoGiroOrbita to set
	 */
	public void setSentidoGiroOrbita(String sentidoGiroOrbita) {
		this.sentidoGiroOrbita = sentidoGiroOrbita;
	}

	/**
	 * @return the posicionAngularInicial
	 */
	public long getPosicionAngularInicial() {
		return posicionAngularInicial;
	}

	/**
	 * @param posicionAngularInicial the posicionAngularInicial to set
	 */
	public void setPosicionAngularInicial(int posicionAngularInicial) {
		this.posicionAngularInicial = posicionAngularInicial;
	}

	public boolean save() {

		return false;

	}

	/**
	 * @return the posicionAngularActual
	 */
	public long getPosicionAngularActual() {
				return ( (posicionAngularActual==0)? posicionAngularInicial:posicionAngularActual);
	}

	/**
	 * @param posicionAngularActual the posicionAngularActual to set
	 */
	public void setPosicionAngularActual(long posicionAngularActual) {
		this.posicionAngularActual = posicionAngularActual;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Planeta [nombre=" + nombre + ", velocidadAngular=" + velocidadAngular + ", distanciaSol=" + distanciaSol
				+ ", sentidoGiroOrbita=" + sentidoGiroOrbita + ", posicionAngularInicial=" + posicionAngularInicial
				+ ", posicionAngularActual=" + posicionAngularActual + "]";
	}

}