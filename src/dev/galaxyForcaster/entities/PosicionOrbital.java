/**
 * 
 */
package dev.galaxyForcaster.entities;

import java.awt.geom.Point2D;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase para modelar una posicioOrbital de un planeta en particular
 * Cuando se crea el objeto se calcula la posicion nueva 
 * @author richard
 *
 */
public class PosicionOrbital implements Serializable {

	/**
	 * 
	 */
	final static Logger log = LoggerFactory.getLogger(PosicionOrbital.class);

	private static final long serialVersionUID = 5826191735682596960L;

	private long IdentificadorPeriodo;
	private String nombrePlaneta;
	private long posicionAngular;
	private double posicionCartesaianaX;
	private double posicionCartesaianaY;
	private Point2D coordenadas;

	/**
	 * 
	 * Cuando se crea el objeto se calcula la posicion nueva a partir de la ultima que tiene el planeta
	 * 
	 * @author richard
	 *
	 */
	public PosicionOrbital(Planeta p, long idPeriodo) {
		// TODO Auto-generated constructor stub
		setNombrePlaneta(p.getNombre());
		setIdentificadorPeriodo(idPeriodo);
		//calculo la siguiente posicion  del planeta para ese dia
		calcularNextPosicionOrbital(p.getVelocidadAngular(), p.getDistanciaSol(), p.getSentidoGiroOrbita(),
				p.getPosicionAngularActual());

	}

	/**
	 * @return the nombrePlaneta
	 */
	public String getNombrePlaneta() {
		return nombrePlaneta;
	}

	/**
	 * @param nombrePlaneta the nombrePlaneta to set
	 */
	public void setNombrePlaneta(String nombrePlaneta) {
		this.nombrePlaneta = nombrePlaneta;
	}

	/**
	 * @return the posicionAngular
	 */
	public long getPosicionAngular() {
		return posicionAngular;
	}

	/**
	 * @param posicionAngular the posicionAngular to set
	 */
	private void setPosicionAngular(long posicionAngular) {
		this.posicionAngular = posicionAngular;
	}

	/**
	 * @return the getCoordenadas
	 */
	public Point2D getCoordenadas() {
		return this.coordenadas;
	}

	/**
	 * @return the posicionCartesaianaX
	 */
	public double getX() {
		return posicionCartesaianaX;
	}

	/**
	 * @param posicionCartesaianaX the posicionCartesaianaX to set
	 */
	public void setPosicionCartesaianaX(long posicionCartesaianaX) {
		this.posicionCartesaianaX = posicionCartesaianaX;
	}

	/**
	 * @return the posicionCartesaianaY
	 */
	public double getY() {
		return posicionCartesaianaY;
	}

	/**
	 * @param posicionCartesaianaY the posicionCartesaianaY to set
	 */
	public void setPosicionCartesaianaY(long posicionCartesaianaY) {
		this.posicionCartesaianaY = posicionCartesaianaY;
	}

	/**
	 * @return the identificadorPeriodo
	 */
	public long getIdentificadorPeriodo() {
		return IdentificadorPeriodo;
	}

	/**
	 * @param identificadorPeriodo the identificadorPeriodo to set
	 */
	public void setIdentificadorPeriodo(long identificadorPeriodo) {
		IdentificadorPeriodo = identificadorPeriodo;
	}

	/**
	 * la siguiente posicion se calcula con la posicion actual mas la velocidad
	 * si es horaria la posicionAngular es decreciente de 360 -->0-->360-->
	 * caso contrario va de 0 -->360-->0-->
	 * luego se calcula el punto x e y usando cambio de coordenadas polares a cartesianas y redondeando a 3 decimales
	 * @param posiscionActual the posiscionActual to set
	 */
	public void calcularNextPosicionOrbital(int velocidadAngular, int distanciaSol, String sentidoGiroOrbita,
			long posicionAngularActual) {

		long posicion = 0;

		if (("HORARIO").equalsIgnoreCase(sentidoGiroOrbita)) {
			posicion = posicionAngularActual - velocidadAngular;
			if (posicion < 0)
				posicion = 360 + posicion;

		}

		if (("ANTIHORARIO").equalsIgnoreCase(sentidoGiroOrbita)) {
			posicion = posicionAngularActual + velocidadAngular;
			if (posicion > 360)
				posicion = posicion - 360;

		}

		this.setPosicionAngular(posicion);
		// ystem.out.println((double)Math.round(number * 100d) / 100d)

		// this.posicionCartesaianaX = (distanciaSol *
		// Math.cos(Math.toRadians(this.posicionAngular)));
		log.debug("coseno de numero " + Math.cos(this.posicionAngular));

		this.posicionCartesaianaX = (double) Math
				.round((distanciaSol * Math.cos(Math.toRadians(this.posicionAngular))) * 1000d) / 1000d;

		// log.debug((double)Math.round(Math.cos(Math.toRadians(this.posicionAngular))
		// * 10000d) / 10000d);

		// this.posicionCartesaianaY = (distanciaSol *
		// Math.sin(Math.toRadians(this.posicionAngular)));
		this.posicionCartesaianaY = (double) Math
				.round((distanciaSol * Math.sin(Math.toRadians(this.posicionAngular))) * 1000d) / 1000d;

		// log.debug((double)Math.round(Math.sin(Math.toRadians(this.posicionAngular))
		// * 10000d) / 10000d);

		this.coordenadas = new Point2D.Double(posicionCartesaianaX, posicionCartesaianaY);
//		this.coordenadas.setLocation(posicionCartesaianaX, posicionCartesaianaY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nPosicionOrbital [IdentificadorPeriodo=" + IdentificadorPeriodo + ", nombrePlaneta=" + nombrePlaneta
				+ ", posicionAngular=" + posicionAngular + ", posicionCartesaianaX=" + posicionCartesaianaX
				+ ", posicionCartesaianaY=" + posicionCartesaianaY + ", coordenadas=" + coordenadas + "]";
	}

}
