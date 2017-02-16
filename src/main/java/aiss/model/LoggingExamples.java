package aiss.model;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingExamples {

	private static final Logger logger = Logger.getLogger(LoggingExamples.class.getName());

	public void doIt() throws SecurityException, IOException {
		logger.entering(getClass().getName(), "doIt");

		try {
			// ... something that can throw an exception
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error doing XYZ", e);
		}

		logger.exiting(getClass().getName(), "doIt");

	}

	public static void main(String[] args) {
		try {
			Logger logger = Logger.getLogger("");
			Logger logger1 = Logger.getLogger("1");
			Logger logger1_2 = Logger.getLogger("1.2");

			logger1.addHandler(new ConsoleHandler());
			logger1_2.addHandler(new ConsoleHandler());
			FileHandler fh=new FileHandler("C:\\workspace\\myLog.txt");
			fh.setFormatter(new SimpleFormatter());
			logger1.addHandler(fh);			

			logger.info("msg:");
			logger1.info("msg: 1");
			logger1_2.info("msg: 1.2");
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}