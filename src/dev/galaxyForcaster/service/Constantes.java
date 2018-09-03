/**
 * 
 */
package dev.galaxyForcaster.service;

import dev.galaxyForcaster.entities.Planeta;
import dev.galaxyForcaster.entities.PosicionOrbital;
import dev.galaxyForcaster.entities.Pronostico;

/**
 * @author richard
 *
 */
public class Constantes {

	/**
	 * 
	 */
	public Constantes() {
		// TODO Auto-generated constructor stub
	}

	public static final String SEQUIA = "SEQUIA";
	public static final String NORMAL = "NORMAL";
	public static final String LLUVIA = "LLUVIA";
	public static final String LLUVIA_INTENSA = "LLUVIA_INTENSA";
	public static final String OPTIMO = "OPTIMO";

	public static final String URL = "jdbc:sqlite:clima.db";

	public static final String SQL_Creacion_Planeta = "CREATE TABLE IF NOT EXISTS " + Planeta.class.getSimpleName()
			+ "(nombre CHAR(50) NOT NULL PRIMARY KEY, velocidadAngular int NOT NULL, distanciaSol int NOT NULL, sentidoGiroOrbita CHAR(20) NOT NULL, posicionAngularInicial int NOT NULL, posicionAngularActual  int NOT NULL);";

	public static final String SQL_Creacion_PosicionOrbital = "CREATE TABLE IF NOT EXISTS "
			+ PosicionOrbital.class.getSimpleName()
			+ "(IdentificadorPeriodo int NOT NULL, nombrePlaneta CHAR(50) NOT NULL, posicionAngular int NOT NULL, posicionCartesaianaX Real NOT NULL, posicionCartesaianaY Real NOT NULL, PRIMARY KEY(nombrePlaneta,IdentificadorPeriodo));";

	public static final String SQL_Creacion_Pronostico = "CREATE TABLE IF NOT EXISTS "
			+ Pronostico.class.getSimpleName()
			+ "(IdentificadorPeriodo int NOT NULL PRIMARY KEY, perimetroArea  REAL NOT NULL, condicionClimatica CHAR(100) NOT NULL);";
	
	public static final String SQL_Drop_Planeta ="DROP TABLE IF EXISTS " + Planeta.class.getSimpleName() +";";
	public static final String SQL_Drop_PosicionOrbital ="DROP TABLE IF EXISTS " + PosicionOrbital.class.getSimpleName() +";";
	public static final String SQL_Drop_Pronostico ="DROP TABLE IF EXISTS " + Pronostico.class.getSimpleName() +";";
}
