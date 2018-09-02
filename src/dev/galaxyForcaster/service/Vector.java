/**
 * 
 */
package dev.galaxyForcaster.service;

import dev.galaxyForcaster.entities.PosicionOrbital;

/**
 * @author richard
 *
 */
public class Vector {

	/**
	 * 
	 */

	private double coordenadaX;
	private double coordenadaY;
	private String planetaA;
	private String planetaB;

	public Vector(PosicionOrbital pA, PosicionOrbital pB) {
		// TODO Auto-generated constructor stub

		this.coordenadaX = pB.getX() - pA.getX();
		this.coordenadaY = pB.getY() - pA.getY();
		this.planetaA = pA.getNombrePlaneta();
		this.planetaB = pB.getNombrePlaneta();

	}

	public Vector(PosicionOrbital pA, double px2, double py2, String planeta) {
		// TODO Auto-generated constructor stub
		this.coordenadaX = px2 - pA.getX();
		this.coordenadaY = py2 - pA.getY();
		this.planetaA = pA.getNombrePlaneta();
		this.planetaB = planeta;
	}

	// calculo de combinacion linear entre vectores
	public boolean isCombinacionLinearDe(Vector v) {

		if (this.coordenadaX == 0 && v.coordenadaX == 0)
			return true;
		
		if (this.coordenadaY == 0 && v.coordenadaY == 0)
			return true;

		if (v.coordenadaX == 0  ||v.coordenadaY == 0 )
			return false;
		
		System.out.println(" divisionX =" + (this.coordenadaX / v.coordenadaX));
		System.out.println(" divisionY =" + (this.coordenadaY / v.coordenadaY));
		System.out
				.println(" comparacion =" + ((this.coordenadaX / v.coordenadaX) == (this.coordenadaY / v.coordenadaY)));

		return ((this.coordenadaX / v.coordenadaX) == (this.coordenadaY / v.coordenadaY));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vector [coordenadaX=" + coordenadaX + ", coordenadaY=" + coordenadaY + ", planetaA=" + planetaA
				+ ", planetaB=" + planetaB + "]";
	}

}
