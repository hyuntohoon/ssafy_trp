package com.ssafy.enjoytrip.model.dao;

import java.util.List;

//import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.dto.AttractionInfo;

//@Mapper
public interface AttractionDao {

  List<AttractionInfo> getAttractionList(String sidoCode, String gugunCode, String type,
      String keyword);

}
