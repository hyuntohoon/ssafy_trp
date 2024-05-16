package com.ssafy.enjoytrip.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.AttractionDao;
import com.ssafy.enjoytrip.model.dto.AttractionInfo;

@Service
public class AttractionServiceImpl implements AttractionService {

	private AttractionDao attractionDao;

	public AttractionServiceImpl(AttractionDao attractionDao) {
		this.attractionDao = attractionDao;
	}

	@Override
	public List<AttractionInfo> getAttractionList(String sidoCode, String gugunCode, String type, String keyword) {
		return attractionDao.getAttractionList(sidoCode, gugunCode, type, keyword);
	}
}
