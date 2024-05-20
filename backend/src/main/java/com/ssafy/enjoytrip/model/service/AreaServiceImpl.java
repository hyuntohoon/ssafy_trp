package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.Gugun;
import com.ssafy.enjoytrip.model.entity.Sido;
import com.ssafy.enjoytrip.model.repository.GugunRepository;
import com.ssafy.enjoytrip.model.repository.SidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private SidoRepository sidoRepository;

	@Autowired
	private GugunRepository gugunRepository;

	@Override
	public List<Sido> getSidoList() {
		return sidoRepository.findAll();
	}

	@Override
	public List<Gugun> getGugunListBySidoCode(Integer sidoCode) {
		return gugunRepository.findBySidoCode(sidoCode);
	}
}
