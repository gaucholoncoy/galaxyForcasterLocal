/**
 * 
 */
package dev.galaxyForcaster.entities;

import java.awt.geom.Point2D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase utilitaria para abstraer el calculo de combinacion lineal 
 * @author richard
 *
 */
public class Vector {

	final static Logger log = LoggerFactory.getLogger(Vector.class);

	/**
	 * 
	 */

	private double coordenadaX;
	private double coordenadaY;
	private String planetaA;
	private String planetaB;

	private Point2D coordenadasVectoriales;

	/**
	 * Cuando se crea el vector v(a,b) se calculan las coordenadas vectoriales y se redondean a 3 decimales
	 * que luego se usan evaluar si los puntos de los vectores estan en la misma recta
	 *  
	 * @author richard
	 *
	 */
	public Vector(Point2D pA, Point2D pB, String planetaA, String planetaB) {
		// TODO Auto-generated constructor stub

		// (double)Math.round((pB.getX() - pA.getX())) * 1000d) / 1000d;
		this.coordenadaX = (double) Math.round((pB.getX() - pA.getX()) * 1000d) / 1000d;

//		this.coordenadaY = pB.getY() - pA.getY();
		this.coordenadaY = (double) Math.round((pB.getY() - pA.getY()) * 1000d) / 1000d;

		this.coordenadasVectoriales = new Point2D.Double((double) Math.round((pB.getX() - pA.getX()) * 1000d) / 1000d,
				(double) Math.round((pB.getY() - pA.getY()) * 1000d) / 1000d);
		this.planetaA = planetaA;
		this.planetaB = planetaB;

	}

//	public Vector(PosicionOrbital pA, PosicionOrbital pB) {
//		// TODO Auto-generated constructor stub
//
//		//(double)Math.round((pB.getX() - pA.getX())) * 1000d) / 1000d;
//		this.coordenadaX = (double) Math.round((pB.getX() - pA.getX()) * 1000d) / 1000d;
//		
////		this.coordenadaY = pB.getY() - pA.getY();
//		this.coordenadaY = (double) Math.round((pB.getY() - pA.getY()) * 1000d) / 1000d;
//		this.planetaA = pA.getNombrePlaneta();
//		this.planetaB = pB.getNombrePlaneta();
//
//	}
//
//	public Vector(PosicionOrbital pA, double px2, double py2, String planeta) {
//		// TODO Auto-generated constructor stub
////		this.coordenadaX = px2 - pA.getX();
//		this.coordenadaX = (double) Math.round((px2 - pA.getX()) * 1000d) / 1000d;		
////		this.coordenadaY = py2 - pA.getY();
//		this.coordenadaY = (double) Math.round((py2 - pA.getY()) * 1000d) / 1000d;		
//		this.planetaA = pA.getNombrePlaneta();
//		this.planetaB = planeta;
//	}

	// calculo de combinacion linear entre vectores
	public boolean isCombinacionLinearDe(Vector v) {

		double componenteX = Math.abs((double) Math.round((this.coordenadaX * v.coordenadaY) * 1000d) / 1000d);
		double componenteY = Math.abs((double) Math.round((this.coordenadaY * v.coordenadaX) * 1000d) / 1000d);

		log.debug(" determinante a*d  =" + (componenteX) + " determinante c*b =" + (componenteY) + " comparacion ="
				+ ((componenteX - componenteY) == 0));

//		return (componenteX == componenteY);
		return ((componenteX - componenteY) == 0 );
//		return ((this.coordenadaX / v.coordenadaX) == (this.coordenadaY / v.coordenadaY));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nVector [coordenadaX=" + coordenadaX + ", coordenadaY=" + coordenadaY + ", planetaA=" + planetaA
				+ ", planetaB=" + planetaB + ", coordenadasVectoriales=" + coordenadasVectoriales + "]";
	}

}
