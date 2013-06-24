package nl.tiemensit.youlesscollector.app;

import java.io.File;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import nl.tiemensit.youlesscollector.model.YoulessProperties;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Collector {

	private static Logger LOGGER = Logger.getLogger(Collector.class);
	private CollectorTask task;
	private ProcessorTask processorTask;
	
	public Collector() {
		LOGGER.info("Starting collector app");
		//task = new CollectorTask();
		processorTask = new ProcessorTask();
	}
	
	public static void main(String [] args) throws Exception {
		Collector app = new Collector();
	    Appender startupAppender = new ConsoleAppender(new SimpleLayout(), "System.err");
	    	if (YoulessProperties.getInstance().getBoolValueByKey("development")) {
	    		app.processorTask.run();
	    	} else {
		    try {
				LOGGER.addAppender(startupAppender);
			       // do sanity checks and startup actions
			    daemonize();
			    addDaemonShutdownHook();
			} catch (Throwable e) {
			       LOGGER.fatal("Startup failed.",e);
			} finally  {
				LOGGER.removeAppender(startupAppender);
			}
			while(!isShutdownRequested()) {
				app.start();
			}
	    	}
	}

	private void start() {
		LOGGER.debug("Starting timer");
		//Timer collector = new Timer();
		//collector.scheduleAtFixedRate(task, 5000, 60000);
		
		Timer processor = new Timer();
		processor.scheduleAtFixedRate(processorTask, 3600000,3600000);
		
	}
	
	static private Thread mainThread;

	static private Thread getMainDaemonThread()	{
		return mainThread;
	}

	static public void daemonize() {
		LOGGER.info("daemonizing...");
	    getPidFile().deleteOnExit();

		mainThread = Thread.currentThread();
		System.out.close();
		System.err.close();
	}


	private static File getPidFile() {
		return new File(System.getProperty("daemon.pidfile"));
	}
	
	static protected boolean shutdownRequested = false;

	static public void shutdown()
	{
	   shutdownRequested = true;

	   try
	   {
	       getMainDaemonThread().join();
	   }
	   catch(InterruptedException e)
	   {
	       LOGGER.error("Interrupted which waiting on main daemon thread to complete.");
	   }
	}

	static public boolean isShutdownRequested()
	{
	   return shutdownRequested;
	}
	
	static protected void addDaemonShutdownHook()
	{
	   Runtime.getRuntime().addShutdownHook( 
			   new Thread() { public void run() { Collector.shutdown(); }});
	}


}
