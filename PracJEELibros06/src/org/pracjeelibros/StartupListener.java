package org.pracjeelibros;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class StartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		String log4jFile = servletContext.getInitParameter("log4jFileName");
		String rutaBase = servletContext.getRealPath(log4jFile);
		DOMConfigurator.configure(rutaBase);
		Logger logger = LogManager.getLogger(this.getClass().getName());
		logger.debug("Se ha cargado el fichero de configuración de log: " + log4jFile);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Logger logger = LogManager.getLogger(this.getClass().getName());
		logger.debug("Aplicación Finalizada");
	}


}
