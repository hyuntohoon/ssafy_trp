package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.entity.Invitation;
import com.ssafy.enjoytrip.model.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invitations")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @PostMapping("/send")
    public ResponseEntity<Invitation> sendInvitation(@RequestParam String fromUserId,
                                                     @RequestParam String toUserId,
                                                     @RequestParam Integer tripPlanId) {
        Invitation invitation = invitationService.sendInvitation(fromUserId, toUserId, tripPlanId);
        return ResponseEntity.ok(invitation);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Invitation>> getInvitationsForUser(@PathVariable String userId) {
        List<Invitation> invitations = invitationService.getInvitationsForUser(userId);
        return ResponseEntity.ok(invitations);
    }

    @PostMapping("/respond")
    public ResponseEntity<Invitation> respondToInvitation(@RequestParam Integer invitationId,
                                                          @RequestParam String status) {
        Invitation invitation = invitationService.respondToInvitation(invitationId, status);
        return ResponseEntity.ok(invitation);
    }
}
