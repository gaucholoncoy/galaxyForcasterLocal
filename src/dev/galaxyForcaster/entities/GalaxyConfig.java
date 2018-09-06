package dev.galaxyForcaster.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import dev.galaxyForcaster.DAO.PlanetaDao;

/**
 * Clase para contener la configuracion inicial en la generacion de metricas que
 * se envian en el json del BODY del END POINT POST /galaxyForcaster/rest/clima/inicio
 * @author richard
 *
 */
public class GalaxyConfig implements Serializable {

	private static final long serialVersionUID = 30603850117689481L;

	// Dias para calcular los pronosticos
	
	private int periodosForcast; 
	
	// Lista de los planetas de la galaxia
	private ArrayList<Planeta> planetas;

	public GalaxyConfig() {
		
	}

	public int getPeriodosForcast() {
		return periodosForcast;
	}

	public void setPeriodosForcast(int periodosForcast) {
		this.periodosForcast = periodosForcast;
	}

	public ArrayList<Planeta> getPlanetas() {
		return planetas;
	}

	public void setPlanetas(ArrayList<Planeta> planetas) {
		this.planetas = planetas;
	}

	// Metodo para hacer la primera persistencia de los planetas en el proceso batch
	public boolean persistPlanetas() {

		Iterator<Planeta> it = planetas.iterator();
		while (it.hasNext()) {
			PlanetaDao.insertar((Planeta) it.next());

		}

		return true;

	}

}
