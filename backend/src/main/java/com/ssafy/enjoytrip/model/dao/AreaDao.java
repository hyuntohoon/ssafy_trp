package com.ssafy.enjoytrip.model.dao;

import java.util.List;

//import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.dto.Gugun;
import com.ssafy.enjoytrip.model.dto.Sido;

//@Mapper
public interface AreaDao {

  public List<Sido> getSidoList();

  public List<Gugun> getGugunList(int sidoCode) ;

}
