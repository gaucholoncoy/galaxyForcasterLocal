/**
 * 
 */
package dev.galaxyForcaster.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.galaxyForcaster.entities.GalaxyConfig;
import dev.galaxyForcaster.entities.Planeta;
import dev.galaxyForcaster.entities.PosicionOrbital;
import dev.galaxyForcaster.entities.Pronostico;

/**
 * @author richard
 *
 */
public class Forcaster {

	/**
	 * 
	 */
	public Forcaster() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<Planeta> planetas = new ArrayList<Planeta>();

		ArrayList<PosicionOrbital> PosicionesOrbitales = new ArrayList<PosicionOrbital>();

		try {
			
			System.out.print("++++++++++++++");
//			for (int x=1;x<380;x++){
//				System.out.print("\n numero " + x + " mod 360 =" + (x % 360) );
//				System.out.print("\n numero " + (x-360) + " mod 360 =" + ((x-360) % 360) );
//				}
		
			
			File  json = new File("C:\\Users\\richard\\Desktop\\mercadolibre\\galaxyForcaster\\src\\dev\\galaxyForcaster\\service\\configGalaxia.json");
			
			GalaxyConfig confGalaxia = new ObjectMapper().readValue(json, GalaxyConfig.class);
			
			ObjectMapper mapper = new ObjectMapper();

			File  json1 = new File("C:\\Users\\richard\\Desktop\\mercadolibre\\galaxyForcaster\\src\\dev\\galaxyForcaster\\service\\configPlanetas.json");
			
			List<Planeta> planetas1 = mapper.readValue(json1, mapper.getTypeFactory().constructCollectionType(List.class, Planeta.class));
			
			confGalaxia.setPlanetas((ArrayList<Planeta>) planetas1);
			
			// Validar json + planetascreacion de planetas , si error ==> excepcion and fin

			if (confGalaxia.persistPlanetas()) {

				planetas = confGalaxia.getPlanetas();

				for (int dia = 1; dia < confGalaxia.getPeriodosForcast(); dia++) {

					
					PosicionesOrbitales.clear();

					Iterator<Planeta> it = planetas.iterator();
					
					Planeta pa =null;
					System.out.println("Ciclo " + dia +" de " + confGalaxia.getPeriodosForcast() );					
					while (it.hasNext()) {
						pa = it.next();
						
						System.out.println(pa.toString());
						
						PosicionOrbital po = new PosicionOrbital(pa, dia);
						
						pa.setPosicionAngularActual(po.getPosicionAngular());
						
						System.out.println(po.toString());
						
						PosicionesOrbitales.add(po);

					}

					Pronostico pron = new Pronostico(dia, PosicionesOrbitales);
					System.out.println("Resultado :" + pron.existeAlineacion());
					System.out.println(pron.toString());

				}
				System.out.println("999991");
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

}
