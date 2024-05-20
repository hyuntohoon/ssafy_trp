package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.Gugun;
import com.ssafy.enjoytrip.model.entity.Sido;

import java.util.List;

public interface AreaService {
  List<Sido> getSidoList();
  List<Gugun> getGugunListBySidoCode(Integer sidoCode);
}
