package com.poc.service;

import java.util.List;

import com.poc.dao.EISCDDao;

public class EISCDService {

	EISCDDao eiscdDao;
	
	public void setEiscdDao(EISCDDao eiscdDao) {
		this.eiscdDao = eiscdDao;
	}

	public boolean process(List eiscd){
		eiscdDao = new EISCDDao();
		
		return eiscdDao.loadEISCD(eiscd);
	}
}
