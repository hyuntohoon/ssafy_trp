package com.ssafy.enjoytrip.model.repository;

import com.ssafy.enjoytrip.model.entity.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer>, JpaSpecificationExecutor<AttractionInfo> {
    // 추가적인 쿼리 메서드가 필요하다면 여기에 정의
}