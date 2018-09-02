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

		//(double)Math.round((pB.getX() - pA.getX())) * 1000d) / 1000d;
		this.coordenadaX = (double) Math.round((pB.getX() - pA.getX()) * 1000d) / 1000d;
		
//		this.coordenadaY = pB.getY() - pA.getY();
		this.coordenadaY = (double) Math.round((pB.getY() - pA.getY()) * 1000d) / 1000d;
		this.planetaA = pA.getNombrePlaneta();
		this.planetaB = pB.getNombrePlaneta();

	}

	public Vector(PosicionOrbital pA, double px2, double py2, String planeta) {
		// TODO Auto-generated constructor stub
//		this.coordenadaX = px2 - pA.getX();
		this.coordenadaX = (double) Math.round((px2 - pA.getX()) * 1000d) / 1000d;		
//		this.coordenadaY = py2 - pA.getY();
		this.coordenadaY = (double) Math.round((py2 - pA.getY()) * 1000d) / 1000d;		
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
		
	
		
		double componenteX= Math.abs((double) Math.round((this.coordenadaX / v.coordenadaX) * 1000d) / 1000d);	
		double componenteY= Math.abs((double) Math.round((this.coordenadaY / v.coordenadaY) * 1000d) / 1000d);	


		System.out
				.println(" divisionY =" + (componenteY) + " divisionX =" + (componenteX) +" comparacion =" + ((componenteX) == (componenteY)));		
				
		return (componenteX == componenteY);
//		return ((this.coordenadaX / v.coordenadaX) == (this.coordenadaY / v.coordenadaY));

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
