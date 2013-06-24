package nl.tiemensit.youlesscollector.app;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import nl.tiemensit.youlesscollector.model.ActualData;
import nl.tiemensit.youlesscollector.model.SqlLiteConnection;
import nl.tiemensit.youlesscollector.service.ActualDataProcessor;
import nl.tiemensit.youlesscollector.service.ActualDataRetriever;

public class CollectorTask extends TimerTask {
	
	private static Logger LOGGER = Logger.getLogger(CollectorTask.class);
	
	private SqlLiteConnection connection;

	private ActualDataRetriever retriever;

	public CollectorTask() {
		LOGGER.info("Creating database connection");
		if (checkSQLDatabase()) {
			connection = new SqlLiteConnection();
		} else {
			LOGGER.error("Could not find any database");
			throw new RuntimeException("Failed to open any database");
		}
	}
	
	private boolean checkSQLDatabase() {
		return SqlLiteConnection.checkSQLDatabase();
	}
		
	@Override
	public void run() {
		if (retriever == null)
			retriever = new ActualDataRetriever();
		ActualDataProcessor processor = new ActualDataProcessor(connection);
		processor.processData(retriever.getActualData());
	}

}
