package nl.tiemensit.youlesscollector.model;

import nl.tiemensit.youlesscollector.service.ProcessedDataDAOImpl;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "powerused", daoClass = ProcessedDataDAOImpl.class)
public class PowerUsedData {
	
	@DatabaseField(generatedId = true)
    private int id;
	
	@DatabaseField
	private long updateDate;
	
	@DatabaseField
	private long power_usage;
	
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



}
