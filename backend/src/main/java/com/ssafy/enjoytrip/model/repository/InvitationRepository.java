package com.ssafy.enjoytrip.model.repository;

import com.ssafy.enjoytrip.model.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
    List<Invitation> findByFromUserId(String fromUserId);
    List<Invitation> findByToUserId(String toUserId);
    List<Invitation> findByTripPlanId(Integer tripPlanId);
}
