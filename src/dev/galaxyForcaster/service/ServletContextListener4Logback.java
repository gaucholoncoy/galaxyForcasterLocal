/**
 * 
 */
package dev.galaxyForcaster.service;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;

/**
 * Aprovecho este listener que el "contextInitialized" y el "contextDestroyed"
 * del modulo web para hacer el:<br>
 * {@code shutdown} de logback.
 * 
 * @author richard
 */
public class ServletContextListener4Logback implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent ctxEvent) {

		ServletContext ctx = ctxEvent.getServletContext();

		// Esta linea hace que se cargue la conf. de logback cuando levanta el
		// modulo web.
		// Sin esto recien cargaria cuando llega el primer mensaje.
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		// log.debug(loggerContext.isStarted());
		Logger rootLogger = lc.getLogger(Logger.ROOT_LOGGER_NAME);

		try {
			rootLogger.info("Server Brand				: {}", ctx.getServerInfo());

			String appPrefix = ctx.getInitParameter("APP_PREFIX");
			if ("".equals(appPrefix)) {
				return;
			}
			rootLogger.info("(web.xml) APP_PREFIX		: {}", appPrefix);
			rootLogger.info("(logback.xml) APP_DIR		: {}", lc.getProperty(appPrefix + ".APP_DIR"));
			rootLogger.info("(logback.xml) APP_LOG		: {}", lc.getProperty(appPrefix + ".APP_LOG"));
		} catch (Exception e) {

			rootLogger.error("Error en los logs de inicio de la aplicacion", e);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent ctxEvent) {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		// Sin esto, si se baja la aplicacion pero no el server,
		// queda un handle "extra" al archivo de log que no esta en uso
		// e impide el normal funcionamiento del RollingFileAppender.
		loggerContext.stop();
	}

}
