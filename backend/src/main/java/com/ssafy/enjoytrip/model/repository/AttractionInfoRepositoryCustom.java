package com.ssafy.enjoytrip.model.repository;

import com.ssafy.enjoytrip.model.entity.AttractionInfo;
import org.locationtech.jts.geom.Point;

import java.util.List;

public interface AttractionInfoRepositoryCustom {
    List<AttractionInfo> findWithinDistance(Point point, double distance);
}
