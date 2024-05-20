package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.entity.Sido;
import com.ssafy.enjoytrip.model.entity.Gugun;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.ssafy.enjoytrip.model.service.AreaService;

@RestController
@RequestMapping("/areasido")
public class AreaRestController {
    private AreaService areaService;

    public AreaRestController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("/sido")
    public ResponseEntity<?> getSido() {
        List<Sido> areas = areaService.getSidoList();
        if (!areas.isEmpty()) {
            return ResponseEntity.ok(areas); // 200
        } else {
            return ResponseEntity.noContent().build(); // 204
        }
    }

    @GetMapping("/gugun/{sidocode}")
    public ResponseEntity<List<Gugun>> getGugun(@PathVariable("sidocode") int sidoCode) {
        List<Gugun> list = areaService.getGugunListBySidoCode(sidoCode);
        if (list != null && !list.isEmpty()) {
            return ResponseEntity.ok(list); // 200 OK with body
        } else {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
    }
}
