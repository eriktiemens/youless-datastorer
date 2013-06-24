package nl.tiemensit.youlesscollector.service;

import java.sql.SQLException;

import nl.tiemensit.youlesscollector.model.PowerUsedData;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class ProcessedDataDAOImpl extends BaseDaoImpl<PowerUsedData, String> implements ProcessedDataAO {
	
	public ProcessedDataDAOImpl(JdbcConnectionSource connectionSource) 
	throws SQLException {
		super(connectionSource, PowerUsedData.class);
	}

}
