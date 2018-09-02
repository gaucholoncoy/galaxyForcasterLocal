/**
 * 
 */
package dev.galaxyForcaster.entities;

import java.util.ArrayList;

import dev.galaxyForcaster.service.Vector;

/**
 * @author richard
 *
 */
public class Pronostico {

	/**
	 * 
	 */

	public static final String AL_TOTAL = "ALINEACION_TOTAL";
	public static final String AL_PAR = "ALINEACION_PARCIAL";
	public static final String NO_AL = "NO_ALINEACION";

	private long IdentificadorPeriodo;

	private String CondicionClimatica;

	private ArrayList<PosicionOrbital> PosicionOrbitales;

	public Pronostico(int Id, ArrayList<PosicionOrbital> po) {
		// TODO Auto-generated constructor stub
		this.IdentificadorPeriodo = Id;
		this.PosicionOrbitales = po;
	}

	void analizarCondiciones() {

	}

	public String existeAlineacion() {

		boolean alineacion_parcial = true;
		boolean alineacion_total = false;

		String resultado = NO_AL;

		PosicionOrbital pA = null;
		PosicionOrbital pB = null;
		PosicionOrbital pC = null;

		Vector vectorAB = null;
		Vector vectorBC = null;

		int p = 0;

		try {
			while (alineacion_parcial && ((p + 2)  < PosicionOrbitales.size())) {

				pA = PosicionOrbitales.get(p);
				pB = PosicionOrbitales.get(p + 1);
				pC = PosicionOrbitales.get(p + 2);

				vectorAB = new Vector(PosicionOrbitales.get(p), PosicionOrbitales.get(p + 1));
				System.out.println(vectorAB);
				vectorBC = new Vector(PosicionOrbitales.get(p + 1), PosicionOrbitales.get(p + 2));
				System.out.println(vectorBC);				
				
				//  verficacion si tienen la misma proporcion ==> son vectore que tienen combinacion linear entre ellos
				alineacion_parcial = alineacion_parcial && (vectorAB.isCombinacionLinearDe(vectorBC));
				p++;

			}

			if (alineacion_parcial) {

				// como tengo alineacion entre planetas, verifico si algun vector entre el sol y
				// algun planeta tiene la misma proporcionalidad.

				resultado = AL_PAR;

				vectorAB = new Vector(PosicionOrbitales.get(1), PosicionOrbitales.get(2));

				// vector con punto en el sol x=0,y=0

				vectorBC = new Vector(PosicionOrbitales.get(1), 0, 0,"SOL");

				System.out.println();
			//  verficacion si tienen la misma proporcion ==> son vectore que tienen combinacion linear entre ellos
				alineacion_total = alineacion_parcial && (vectorAB.isCombinacionLinearDe(vectorAB) );
				System.out.println(vectorAB);
				System.out.println(vectorBC);
				

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("perror " + PosicionOrbitales.size());
			e.printStackTrace();
		}

		if (alineacion_total)
			resultado = AL_TOTAL;
		return resultado;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pronostico [IdentificadorPeriodo=" + IdentificadorPeriodo + ", CondicionClimatica=" + CondicionClimatica
				+ ", PosicionOrbitales=" + PosicionOrbitales + "]";
	}

}
