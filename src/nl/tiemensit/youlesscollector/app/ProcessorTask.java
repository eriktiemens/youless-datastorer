package nl.tiemensit.youlesscollector.app;

import java.util.TimerTask;

import nl.tiemensit.youlesscollector.model.SqlLiteConnection;
import nl.tiemensit.youlesscollector.service.PowerUsageAggregator;

import org.apache.log4j.Logger;

public class ProcessorTask extends TimerTask {
	
	private static Logger LOGGER = Logger.getLogger(ProcessorTask.class);
	
	private SqlLiteConnection connection;
	
	@Override
	public void run() {
		openConnection();
		if (connection != null) {
			PowerUsageAggregator aggregator = new PowerUsageAggregator(connection);
			
			
		}
			
	}



	private void openConnection() {
		if (SqlLiteConnection.checkSQLDatabase()) {
			connection = new SqlLiteConnection();
		}
	}


}
