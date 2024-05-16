package com.ssafy.enjoytrip.model.service;

import java.util.List;

import com.ssafy.enjoytrip.model.dto.Gugun;
import com.ssafy.enjoytrip.model.dto.Sido;

public interface AreaService {

  public List<Sido> getSidoList();

  public List<Gugun> getGugunList(int sidoCode);

}
