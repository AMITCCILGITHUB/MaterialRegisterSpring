package org.map.logger;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtil {

	private static LoggerUtil loggerUtil = null;
	protected final static Logger logger = Logger.getLogger("MaterialLogger");

	static {
		try {
			loggerUtil = new LoggerUtil();

			File propFile = new File("resources/logging.properties");
			PropertyConfigurator.configure(propFile.getAbsolutePath());
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static LoggerUtil getLogger() {

		return loggerUtil;
	}

	public void info(Throwable e) {

		logger.info(e);
	}

	public void info(String msg) {

		logger.info(msg);
	}

	public void error(Throwable e) {

		logger.error(e);
	}

	public void error(String msg) {

		logger.error(msg);
	}

	public void warn(Throwable e) {

		logger.warn(e);
	}

	public void warn(String msg) {

		logger.warn(msg);
	}

	public void debug(Throwable e) {

		logger.debug(e);
	}

	public void debug(String msg) {

		logger.debug(msg);
	}
}