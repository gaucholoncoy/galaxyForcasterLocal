/**
 * 
 */
package dev.galaxyForcaster.service;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class RESTServices extends ResourceConfig {
	public RESTServices() {
		packages("com.fasterxml.jackson.jaxrs.json");
		packages("dev.galaxyForcaster.service");
	}
}
