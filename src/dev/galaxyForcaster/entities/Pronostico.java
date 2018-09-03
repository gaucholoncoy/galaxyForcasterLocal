/**
 * 
 */
package dev.galaxyForcaster.entities;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import dev.galaxyForcaster.service.Constantes;

/**
 * @author richard
 *
 */
public class Pronostico {

	/**
	 * 
	 */

	private long identificadorPeriodo;

	private double perimetroArea = 0;

	private String condicionClimatica = Constantes.NORMAL;

	private ArrayList<PosicionOrbital> posicionOrbitales;

	public Pronostico(int Id, ArrayList<PosicionOrbital> po) {
		// TODO Auto-generated constructor stub
		this.identificadorPeriodo = Id;
		this.posicionOrbitales = po;
	}

	public void analizarCondiciones() {

		Point2D puntoA = posicionOrbitales.get(0).getCoordenadas();
		Point2D puntoB = posicionOrbitales.get(1).getCoordenadas();
		Point2D puntoC = posicionOrbitales.get(2).getCoordenadas();
		Point2D pSol = new Point2D.Double(0, 0);

		Vector vectorAB = new Vector(puntoA, puntoB, posicionOrbitales.get(0).getNombrePlaneta(),
				posicionOrbitales.get(1).getNombrePlaneta());
		System.out.println(vectorAB);

		Vector vectorBC = new Vector(puntoB, puntoC, posicionOrbitales.get(1).getNombrePlaneta(),
				posicionOrbitales.get(2).getNombrePlaneta());
		System.out.println(vectorBC);

		Vector vectorSol = new Vector(puntoB, pSol, posicionOrbitales.get(1).getNombrePlaneta(), "SOL");

		System.out.println(vectorSol);

		if (vectorAB.isCombinacionLinearDe(vectorBC)) {

			System.out.println("hay combinacion lineal 0");

//			vectorAB = new Vector(puntoB, puntoC, posicionOrbitales.get(1).getNombrePlaneta(),posicionOrbitales.get(2).getNombrePlaneta());

			// vector con punto en el sol x=0,y=0

//			vectorBC = new Vector(puntoB, puntoC, posicionOrbitales.get(1).getNombrePlaneta(), "SOL");

			if (vectorBC.isCombinacionLinearDe(vectorSol)) {
				condicionClimatica = Constantes.SEQUIA;
				System.out.println("hay combinacion lineal 1");
			} else {
				condicionClimatica = Constantes.OPTIMO;
				System.out.println("hay combinacion lineal 2");
			}

			this.perimetroArea = 0;

		} else {
			Triangulo triangulo = new Triangulo(puntoA, puntoB, puntoC, pSol);
			if (triangulo.isPuntoInTriangulo())
				condicionClimatica = Constantes.LLUVIA;
			this.perimetroArea = triangulo.getPerimetro();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nPronostico [identificadorPeriodo=" + identificadorPeriodo + ", perimetroArea=" + perimetroArea
				+ ", condicionClimatica=" + condicionClimatica + "]";
	}

	/**
	 * @return the identificadorPeriodo
	 */
	public long getIdentificadorPeriodo() {
		return identificadorPeriodo;
	}

	/**
	 * @param identificadorPeriodo the identificadorPeriodo to set
	 */
	public void setIdentificadorPeriodo(long identificadorPeriodo) {
		this.identificadorPeriodo = identificadorPeriodo;
	}

	/**
	 * @return the perimetroArea
	 */
	public double getPerimetroArea() {
		return perimetroArea;
	}

	/**
	 * @param perimetroArea the perimetroArea to set
	 */
	public void setPerimetroArea(long perimetroArea) {
		this.perimetroArea = perimetroArea;
	}

	/**
	 * @return the condicionClimatica
	 */
	public String getCondicionClimatica() {
		return condicionClimatica;
	}

	/**
	 * @param condicionClimatica the condicionClimatica to set
	 */
	public void setCondicionClimatica(String condicionClimatica) {
		this.condicionClimatica = condicionClimatica;
	}

}
