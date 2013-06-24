package nl.tiemensit.youlesscollector.model;

import java.sql.SQLException;


import nl.tiemensit.youlesscollector.service.ActualDataDAOImpl;
import nl.tiemensit.youlesscollector.service.ProcessedDataDAOImpl;

import org.apache.log4j.Logger;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class SqlLiteConnection {

	private static Logger LOGGER = Logger.getLogger(SqlLiteConnection.class);
	
	private static ActualDataDAOImpl dao;
	
	private static ProcessedDataDAOImpl processedDao;

	private static String databaseUrl;

	public SqlLiteConnection() {
		databaseUrl = getDatabaseUrl();
	}
	
	public void writeData(ActualData data) {
		try {
			dao.create(data);
		} catch (SQLException e) {
			LOGGER.error("Writing data failed.",e);
		}

	}
	
	public static boolean checkSQLDatabase() {
		ConnectionSource source;
		try {
			
			source = new JdbcConnectionSource(getUrl());
			setupDataBase(source);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	private static String getUrl() {
		if (databaseUrl == null) {
			databaseUrl = getDatabaseUrl();
		}
		return databaseUrl;
	}

	private static void setupDataBase(ConnectionSource source) throws SQLException {
		dao = DaoManager.createDao(source, ActualData.class);
		processedDao = DaoManager.createDao(source, PowerUsedData.class);
		if (!dao.isTableExists())
			TableUtils.createTable(source, ActualData.class);
		if (!processedDao.isTableExists())
			TableUtils.createTable(source,PowerUsedData.class);
	}
	

	public static String getDatabaseUrl() {
		YoulessProperties properties = YoulessProperties.getInstance();
		return properties.getValueByKey("data.file");
	}
	
	public static ProcessedDataDAOImpl getProcessedDao() {
		return processedDao;
	}
	
	public static ActualDataDAOImpl getActualDataDAO() {
		return dao;
	}


}
