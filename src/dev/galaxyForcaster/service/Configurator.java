/**
 * 
 */
package dev.galaxyForcaster.service;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.galaxyForcaster.DAO.DBHelper;
import dev.galaxyForcaster.DAO.PlanetaDao;
import dev.galaxyForcaster.DAO.PosicionOrbitalDao;
import dev.galaxyForcaster.DAO.PronosticoDao;
import dev.galaxyForcaster.entities.GalaxyConfig;
import dev.galaxyForcaster.entities.Planeta;
import dev.galaxyForcaster.entities.PosicionOrbital;
import dev.galaxyForcaster.entities.Pronostico;

/**
 * Clase utilitaria para hacer el proceso batch de la generacion de pronosticos 
 * en funcion al json recibido en el END POINT 
 * tambien se puede usar de metodo main que seria lo mismo

 * @author richard
 *
 */
public class Configurator {

	final static Logger log = LoggerFactory.getLogger(Configurator.class);

	/**
	 * 
	 */
	public Configurator() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			log.debug(" Query getStatusService ");

			String filePath = "C:\\Users\\richard\\Desktop\\mercadolibre\\galaxyForcaster\\src\\dev\\galaxyForcaster\\service\\configGalaxia.json";

			String json = Configurator.readFileToString(filePath);

			 Configurator.init(json);

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

	// http://www.mysamplecode.com/2011/07/java-convert-file-to-string.html
	// no me parecio necesario usar jar de apache commons

	private static String readFileToString(String filePath) throws java.io.IOException {

		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];

		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		reader.close();
		log.debug(fileData.toString());
		return fileData.toString();
	}

	/**
	 * @param args
	 */
	public static String init(String configuracion) {

		GalaxyConfig config = null;

		ArrayList<Planeta> planetas = new ArrayList<Planeta>();

		ArrayList<PosicionOrbital> PosicionesOrbitales = new ArrayList<PosicionOrbital>();

		try {

			log.debug(" ++++++++++++++");

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			config = mapper.readValue(configuracion, GalaxyConfig.class);

			// crear la db.
			DBHelper.createNewDatabase();
			DBHelper.dropTables();
			DBHelper.createTables();

			if (config.persistPlanetas()) {

				planetas = config.getPlanetas();

				for (int dia = 1; dia <= config.getPeriodosForcast(); dia++) {

					PosicionesOrbitales.clear();

					Iterator<Planeta> it = planetas.iterator();

					Planeta pa = null;
					log.debug("\n Ciclo " + dia + " de " + config.getPeriodosForcast());
					while (it.hasNext()) {
						pa = it.next();

						log.debug(pa.toString());

						PosicionOrbital po = new PosicionOrbital(pa, dia);

						pa.setPosicionAngularActual(po.getPosicionAngular());

						PlanetaDao.update(pa);

						log.debug(po.toString());

						PosicionOrbitalDao.insertar(po);

						PosicionesOrbitales.add(po);

					}

					Pronostico pron = new Pronostico(dia, PosicionesOrbitales);

					pron.analizarCondiciones();

					PronosticoDao.insertar(pron);

					log.debug(pron.toString());
					log.debug("\n Fin Ciclo " + dia + " de " + config.getPeriodosForcast());
				}
				log.debug("FIN");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "OK";
		// TODO Auto-generated method stub

	}

}
