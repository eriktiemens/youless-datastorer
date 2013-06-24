package nl.tiemensit.youlesscollector.service;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import nl.tiemensit.youlesscollector.model.ActualData;
import nl.tiemensit.youlesscollector.model.SqlLiteConnection;

public class PowerUsageAggregator {

	private SqlLiteConnection connection;

	public PowerUsageAggregator(SqlLiteConnection connection) {
		this.connection = connection;
	}
	
	public void aggregateData() throws SQLException {
		List<ActualData> actualDataForOneHour = connection.getActualDataDAO().getActualDataForOneHour();
		
		for (ActualData actualData : actualDataForOneHour) {
			System.out.println(actualData.getMeter_stand());
		}
		
	}
}
