package com.ssafy.enjoytrip.model.service;

import java.util.List;

import com.ssafy.enjoytrip.model.dto.AttractionInfo;

public interface AttractionService {

  public List<AttractionInfo> getAttractionList(String sidoCode, String gugunCode, String type, String keyword);

}
