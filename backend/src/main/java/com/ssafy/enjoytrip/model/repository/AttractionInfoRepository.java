package com.ssafy.enjoytrip.model.repository;

import com.ssafy.enjoytrip.model.entity.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer>, JpaSpecificationExecutor<AttractionInfo> {
    // 추가적인 쿼리 메서드가 필요하다면 여기에 정의
    @Query("SELECT a FROM AttractionInfo a WHERE a.addr1 LIKE %:keyword% AND a.contentTypeId = :contentTypeId ORDER BY a.readcount DESC limit 8")
    List<AttractionInfo> findTop8ByKeywordAndContentTypeIdOrderByReadcountDesc(@Param("keyword") String keyword, @Param("contentTypeId") int contentTypeId);
}