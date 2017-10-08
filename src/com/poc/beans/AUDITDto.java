package com.poc.beans;

import java.sql.Date;
import java.sql.Timestamp;

public class AUDITDto {

	
	private String jobName;
	private Timestamp currentDate;
	private String fileName;
	private String status;
	
	public String getJobName() {
		return jobName;
	}
	
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Timestamp getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Timestamp timestamp) {
		this.currentDate = timestamp;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
