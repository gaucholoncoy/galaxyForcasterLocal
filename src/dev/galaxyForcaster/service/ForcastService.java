package dev.galaxyForcaster.service;

import java.util.ArrayList;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import dev.galaxyForcaster.entities.Estadistica;
import dev.galaxyForcaster.entities.Pronostico;

/**
 * Clase del servicio de atencion de los EP
 * 
 * @author richard
 *
 */

@Path("clima")
public class ForcastService {

	final static Logger log = LoggerFactory.getLogger(ForcastService.class);

	public ForcastService() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Metodo de atencion del EP 	POST  /galaxyForcaster/rest/clima/inicio
	 * 
	 * @author richard
	 *
	 */
	@POST
	@Path("inicio")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_HTML)

	public String insert(String config) {
		String resultado = "";
		resultado = Configurator.init(config);
		return "<html lang=\"en\"><body><h1>" + resultado + "</h1></body></html>";
	}

	
	
	/**
	 * Metodo de atencion del EP •	GET   /galaxyForcaster/rest/clima?dia=xxxxx
	 * 
	 * @author richard
	 *
	 */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPronosticoxDia(@QueryParam("dia") int dia) {

		if (dia == 0 || dia > 3560)
			return Response.serverError().entity("Valor invalido").build();

		Pronostico pro = new Pronostico();
		// if (dia < 1 || dia > 3560)
		// return "Error " ;
		log.debug(" parametro " + dia);

		String respuesta = pro.consultarPronostico(dia);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode json = mapper.createObjectNode();

		json.put("clima", respuesta);
		String result = "" + json;
		return Response.status(200).entity(result).build();

	}
	
	/**
	 * Metodo de atencion del EP 	GET   •	GET /galaxyForcaster/rest/clima/estadistica
	 * 
	 * @author richard
	 *
	 */

	@GET
	@Path("estadistica")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarMetricas() {

		List<Estadistica> estadisticas = new ArrayList<Estadistica>();

		Pronostico pro = new Pronostico();

		estadisticas = pro.consultarEstadisticasPeriodos();

//
		log.debug(" consultarMetricas ");

		ObjectMapper mapper = new ObjectMapper();

		String arrayToJson = null;

		try {
			arrayToJson = mapper.writeValueAsString(estadisticas);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(arrayToJson).build();

	}
}
