package dev.galaxyForcaster.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import dev.galaxyForcaster.DAO.PlanetaDao;



public class GalaxyConfig implements Serializable {
	
	private static final long serialVersionUID = 30603850117689481L;
	
	private int periodosForcast;
	private ArrayList<Planeta> planetas;

	public GalaxyConfig() {
		// TODO Auto-generated constructor stub
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

	public boolean persistPlanetas() {

		Iterator<Planeta> it = planetas.iterator();
		while (it.hasNext()) {
			PlanetaDao.insertar((Planeta) it.next());

		}

		return true;

	}

}
