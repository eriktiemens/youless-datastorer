package nl.tiemensit.youlesscollector.model;

import net.sf.json.JSONObject;
import nl.tiemensit.youlesscollector.service.ActualDataDAOImpl;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "actualdata", daoClass = ActualDataDAOImpl.class)
public class ActualData {
	
	public static String UPDATEDATE = "updateDate";
	
	public ActualData() {}
	
	@DatabaseField(generatedId = true)
    private int id;
	
	@DatabaseField
	private long updateDate;
	
	@DatabaseField
	private long power_usage;
	
	@DatabaseField
	private double meter_stand;

	public ActualData(JSONObject data) {
		this.updateDate = System.currentTimeMillis()/1000;
		this.power_usage = data.getLong("pwr");
		this.setMeter_stand(data.getDouble("cnt"));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUpdateDate(long updateDate) {
		this.updateDate = updateDate;
	}

	public long getUpdateDate() {
		return updateDate;
	}

	public void setPower_usage(long power_usage) {
		this.power_usage = power_usage;
	}

	public long getPower_usage() {
		return power_usage;
	}

	public void setMeter_stand(double meter_stand) {
		this.meter_stand = meter_stand;
	}

	public double getMeter_stand() {
		return meter_stand;
	}
}
