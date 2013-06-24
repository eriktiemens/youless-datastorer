package nl.tiemensit.youlesscollector.service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nl.tiemensit.youlesscollector.model.ActualData;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

public class ActualDataDAOImpl extends BaseDaoImpl<ActualData, String> implements ActualDataDAO {
	
	public ActualDataDAOImpl(JdbcConnectionSource connectionSource) 
		throws SQLException {
		super(connectionSource, ActualData.class);
	}

	@Override
	public List<ActualData> getActualDataForOneHour() throws SQLException {
		Date now = new Date();
		
		QueryBuilder<ActualData, String> queryBuilder = queryBuilder();
		
		Where<ActualData, String> where = queryBuilder.where();
		where.between(ActualData.UPDATEDATE, subtractFromDate(now, 1), now);
		
		return queryBuilder.query();
	}

	private Date subtractFromDate(Date now, int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.HOUR, -hour);
		
		return cal.getTime();
	}

}
