package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.AttractionInfo;

import java.util.List;

public interface AttractionService {
  List<AttractionInfo> getAttractionList(String sidoCode, String gugunCode, String type, String keyword);
}
