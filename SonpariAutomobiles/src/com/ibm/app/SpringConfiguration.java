package com.ibm.app;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
public class SpringConfiguration 
{
	final static Logger logger = Logger.getLogger(Application.class);

	private static ClassPathXmlApplicationContext context = null;

	public SpringConfiguration() {
		logger.debug("SpringConfiguration constructor...");
	}

	public static void loadingSpringBeans() {
		logger.debug("initializing the spring beans started...");
		context = new ClassPathXmlApplicationContext("springConfig.xml");
		logger.debug("initializing the spring beans completed...");
		 
	}
}
