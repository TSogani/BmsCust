package com.poc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.poc.beans.AUDITDto;
import com.poc.dao.EISCDDao;

public class EISCDService {

	EISCDDao eiscdDao;
	
	public void setEiscdDao(EISCDDao eiscdDao) {
		this.eiscdDao = eiscdDao;
	}

	@Transactional
	public boolean process(List eiscd){
		//eiscdDao = new EISCDDao();
		
		return eiscdDao.getInsert(eiscd);
	}
	
	public void updteAUDIT(AUDITDto dto){
		eiscdDao.updateAUDTBL(dto);
	}
}
