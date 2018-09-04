/**
 * 
 */
package dev.galaxyForcaster.entities;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.galaxyForcaster.DAO.DBHelper;
import dev.galaxyForcaster.DAO.PronosticoDao;
import dev.galaxyForcaster.service.Constantes;
import dev.galaxyForcaster.service.ForcastService;

/**
 * @author richard
 *
 */
@XmlRootElement
public class Pronostico implements Serializable {
	
	
	final static Logger log = LoggerFactory.getLogger(ForcastService.class);
	
	
	private static final long serialVersionUID = 6826191735682596960L;
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

	public Pronostico() {
		// TODO Auto-generated constructor stub
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
	public void setPerimetroArea(double perimetroArea) {
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

	/**
	 * @param consultarPronostico the consultarPronostico to set
	 */
	public String consultarPronostico(int dia) {
		
		Pronostico pron = PronosticoDao.consultarPronostico(dia);
		
		log.debug(" condicion climatica" + pron.getCondicionClimatica());
		
		return pron.getCondicionClimatica();
	}

}
