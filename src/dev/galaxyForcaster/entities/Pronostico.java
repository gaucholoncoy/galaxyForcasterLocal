/**
 * 
 */
package dev.galaxyForcaster.entities;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.galaxyForcaster.DAO.PronosticoDao;
import dev.galaxyForcaster.service.Constantes;
import dev.galaxyForcaster.service.ForcastService;

/**
 * Clase para tracker la informacion de el  pronistico de un dia en particular
 * tambien tiene la logica para analizar y definir cual es la condicion climatica del dia
 *   * 
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

	/**
	 * Metodo para analiza y decidir la condicion del dia
	 * sinteticamente se toman las posiciones orbitales de los planetas
	 * se arman 3 vectores AB, AC y AP(entre planeta A y el sol)
	 * y se comprueban si existe combinacion lineal entre AB y AC y luego entre AB y AP
	 * tambien se evalua si el punto(0,0) esta incluido en tringulo formado por los punto A,B y C
	 *  para tracker la informacion de el  pronistico de un dia en particular
	 * tambien tiene la logica para analizar y definir cual es la condicion climatica del dia
	 *   * 
	 * @author richard
	 *
	 */
	public void analizarCondiciones() {

		Point2D puntoA = posicionOrbitales.get(0).getCoordenadas();
		Point2D puntoB = posicionOrbitales.get(1).getCoordenadas();
		Point2D puntoC = posicionOrbitales.get(2).getCoordenadas();
		Point2D pSol = new Point2D.Double(0, 0);

		Vector vectorAB = new Vector(puntoA, puntoB, posicionOrbitales.get(0).getNombrePlaneta(),
				posicionOrbitales.get(1).getNombrePlaneta());
		log.debug(vectorAB.toString());

//		Vector vectorBC = new Vector(puntoB, puntoC, posicionOrbitales.get(1).getNombrePlaneta(),
//				posicionOrbitales.get(2).getNombrePlaneta());

		Vector vectorAC = new Vector(puntoA, puntoC, posicionOrbitales.get(0).getNombrePlaneta(),
				posicionOrbitales.get(2).getNombrePlaneta());
		
		//log.debug(vectorBC.toString());
		log.debug(vectorAC.toString());

//		Vector vectorSol = new Vector(puntoB, pSol, posicionOrbitales.get(1).getNombrePlaneta(), "SOL");
		Vector vectorSol = new Vector(puntoA, pSol, posicionOrbitales.get(0).getNombrePlaneta(), "SOL");
		log.debug(vectorSol.toString());

//		if (vectorAB.isCombinacionLinearDe(vectorBC)) {
		
		if (vectorAB.isCombinacionLinearDe(vectorAC)) {
			log.debug("hay combinacion lineal 0");

//			vectorAB = new Vector(puntoB, puntoC, posicionOrbitales.get(1).getNombrePlaneta(),posicionOrbitales.get(2).getNombrePlaneta());

			// vector con punto en el sol x=0,y=0

//			vectorBC = new Vector(puntoB, puntoC, posicionOrbitales.get(1).getNombrePlaneta(), "SOL");

			if (vectorAB.isCombinacionLinearDe(vectorSol)) {
				condicionClimatica = Constantes.SEQUIA;
				log.debug("hay combinacion lineal 1");
			} else {
				condicionClimatica = Constantes.OPTIMO;
				log.debug("hay combinacion lineal 2");
			}

			this.perimetroArea = 0;

		} else {
			Triangulo triangulo = new Triangulo(puntoA, puntoB, puntoC, pSol);
			if (triangulo.isPuntoInTriangulo())
				condicionClimatica = Constantes.LLUVIA;
			this.perimetroArea = (double) Math.round((triangulo.getPerimetro() * 1000d) / 1000d);

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

	/**
	 * @param consultarEstadisticasPeriodos the consultarEstadisticasPeriodos to set
	 */
	public List<Estadistica> consultarEstadisticasPeriodos() {

		LinkedHashMap<String, String> estadisticasPeriodos = new LinkedHashMap<String, String>();

		estadisticasPeriodos = PronosticoDao.consultarEstadisticasPeriodos();

		List<Estadistica> estadisticas = new ArrayList<Estadistica>();

		for (Map.Entry<String, String> entry : estadisticasPeriodos.entrySet()) {
			String periodo = entry.getKey();
			String cantidad = entry.getValue();
			Estadistica est = new Estadistica("Totales por tipo de clima", periodo, cantidad);
			estadisticas.add(est);
		}

		estadisticasPeriodos.clear();

		estadisticasPeriodos = PronosticoDao.consultarestadisticasLLuvia();

		for (Map.Entry<String, String> entry : estadisticasPeriodos.entrySet()) {
			String IdentificadorPeriodo = entry.getKey();
			String perimetroArea = entry.getValue();
			Estadistica est = new Estadistica("Pico Max lluvia", IdentificadorPeriodo, perimetroArea);
			estadisticas.add(est);
		}

		estadisticasPeriodos = PronosticoDao.consultarEstadisticasPeriodos();

		log.debug(" consultarEstadisticasPeriodos");

		return estadisticas;
	}

}
