package com.ssafy.enjoytrip.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.AreaDao;
import com.ssafy.enjoytrip.model.dto.Gugun;
import com.ssafy.enjoytrip.model.dto.Sido;

@Service
public class AreaServiceImpl implements AreaService {

	private AreaDao areaDao;

	public AreaServiceImpl(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	@Override
	public List<Sido> getSidoList() {
		return areaDao.getSidoList();
	}

	@Override
	public List<Gugun> getGugunList(int sidoCode) {
		return areaDao.getGugunList(sidoCode);
	}

}
