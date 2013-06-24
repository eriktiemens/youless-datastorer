package nl.tiemensit.youlesscollector.service;

import net.sf.json.JSONObject;
import nl.tiemensit.youlesscollector.model.ActualData;
import nl.tiemensit.youlesscollector.model.SqlLiteConnection;

public class ActualDataProcessor {
	
	private SqlLiteConnection connection;

	public ActualDataProcessor(SqlLiteConnection connection) {
		this.connection = connection;
	}

	public void processData(JSONObject data) {
		connection.writeData(new ActualData(data));
	}
	
	
}
