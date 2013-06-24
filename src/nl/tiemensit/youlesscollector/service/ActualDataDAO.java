package nl.tiemensit.youlesscollector.service;


import java.sql.SQLException;
import java.util.List;

import nl.tiemensit.youlesscollector.model.ActualData;

import com.j256.ormlite.dao.Dao;

public interface ActualDataDAO extends Dao<ActualData, String> {
	
	public List<ActualData> getActualDataForOneHour() throws SQLException;
	
}
