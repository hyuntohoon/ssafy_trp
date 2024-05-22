package com.ssafy.enjoytrip.model.repository;

import com.ssafy.enjoytrip.model.entity.AttractionInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttractionInfoRepositoryImpl{
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<AttractionInfo> findWithinDistance(Point point, double distance) {
//        String queryStr = "SELECT a FROM AttractionInfo a WHERE ST_DWithin(a.location, :point, :distance) = true ORDER BY a.readcount DESC";
//        return entityManager.createQuery(queryStr, AttractionInfo.class)
//                .setParameter("point", point)
//                .setParameter("distance", distance)
//                .setMaxResults(8)
//                .getResultList();
//    }
}
