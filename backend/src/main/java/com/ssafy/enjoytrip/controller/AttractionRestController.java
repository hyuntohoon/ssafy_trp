package com.ssafy.enjoytrip.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.ssafy.enjoytrip.model.dto.AttractionInfo;
import com.ssafy.enjoytrip.model.service.AttractionService;

@RestController
@RequestMapping("/attractions")
public class AttractionRestController {
	private AttractionService attractionService;

	public AttractionRestController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}

	@GetMapping
	public ResponseEntity<?> getList(
			@RequestParam(required = false) String sidoCode,
			@RequestParam(required = false) String gugunCode,
			@RequestParam(required = false) String type,
			@RequestParam(required = false) String keyword) {

		System.out.println(sidoCode + " " + gugunCode + " " + type + " " + keyword);
		List<AttractionInfo> attractions = attractionService.getAttractionList(sidoCode, gugunCode, type, keyword);
		if (attractions != null && !attractions.isEmpty()) {
			return ResponseEntity.ok(attractions); // 200
		} else {
			return ResponseEntity.noContent().build(); // 204
		}
	}

}
