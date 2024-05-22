package com.ssafy.enjoytrip.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.ssafy.enjoytrip.model.entity.AttractionInfo;
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
	@GetMapping("/recommended")
	public ResponseEntity<?> getRecommendedAttractions(
			@RequestParam String keyword,
			@RequestParam int contentTypeId) {

		List<AttractionInfo> recommendedAttractions = attractionService.searchAttractions(keyword, contentTypeId);

		if (recommendedAttractions != null && !recommendedAttractions.isEmpty()) {
			return ResponseEntity.ok(recommendedAttractions); // 200
		} else {
			return ResponseEntity.noContent().build(); // 204
		}
	}
	@GetMapping("/gpt")
	public ResponseEntity<?> forGpt(
			@RequestParam(required = false) String sidoCode,
			@RequestParam(required = false) String gugunCode,
			@RequestParam(required = false) String type,
			@RequestParam(required = false) String keyword) {

		List<AttractionInfo> attractions = attractionService.getAttractionListTopTen(sidoCode, gugunCode, type, keyword);
		if (attractions != null && !attractions.isEmpty()) {
			return ResponseEntity.ok(attractions); // 200
		} else {
			return ResponseEntity.noContent().build(); // 204
		}
	}

	@GetMapping("/id")
	public ResponseEntity<?> getAttractionById(
			@RequestParam int id) {
		AttractionInfo attractionInfo = attractionService.getAttractionById(id);

		if (attractionInfo != null) {
			return ResponseEntity.ok(attractionInfo); // 200
		} else {
			return ResponseEntity.noContent().build(); // 204
		}
	}
}
