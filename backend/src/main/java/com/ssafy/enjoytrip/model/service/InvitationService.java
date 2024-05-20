package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.Invitation;
import com.ssafy.enjoytrip.model.entity.User;
import com.ssafy.enjoytrip.model.entity.TripPlan;
import com.ssafy.enjoytrip.model.repository.InvitationRepository;
import com.ssafy.enjoytrip.model.repository.UserRepository;
import com.ssafy.enjoytrip.model.repository.TripPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripPlanRepository tripPlanRepository;

    public Invitation sendInvitation(String fromUserId, String toUserId, Integer tripPlanId) {
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid fromUser ID: " + fromUserId));
        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid toUser ID: " + toUserId));
        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tripPlan ID: " + tripPlanId));

        // tripPlan의 user_id가 fromUserId와 같은지 확인
        if (!tripPlan.getUserId().equals(fromUserId)) {
            throw new IllegalArgumentException("The user ID of the trip plan does not match the fromUser ID");
        }

        Invitation invitation = new Invitation();
        invitation.setFromUser(fromUser);
        invitation.setToUser(toUser);
        invitation.setTripPlan(tripPlan);
        invitation.setStatus("pending");
        return invitationRepository.save(invitation);
    }

    public List<Invitation> getInvitationsForUser(String userId) {
        return invitationRepository.findByToUserId(userId);
    }

    public Invitation respondToInvitation(Integer invitationId, String status) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid invitation ID: " + invitationId));
        invitation.setStatus(status);
        return invitationRepository.save(invitation);
    }
}
