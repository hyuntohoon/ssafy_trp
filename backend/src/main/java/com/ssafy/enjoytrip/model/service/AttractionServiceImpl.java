package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.AttractionInfo;
import com.ssafy.enjoytrip.model.repository.AttractionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class AttractionServiceImpl implements AttractionService {

	@Autowired
	private AttractionInfoRepository attractionInfoRepository;

	@Override
	public List<AttractionInfo> getAttractionList(String sidoCode, String gugunCode, String type, String keyword) {
		Specification<AttractionInfo> spec = Specification.where(null);

		if (StringUtils.hasText(sidoCode)) {
			spec = spec.and((root, query, criteriaBuilder) ->
					criteriaBuilder.equal(root.get("sidoCode"), sidoCode));
		}

		if (StringUtils.hasText(gugunCode)) {
			spec = spec.and((root, query, criteriaBuilder) ->
					criteriaBuilder.equal(root.get("gugunCode"), gugunCode));
		}

		if (StringUtils.hasText(type)) {
			spec = spec.and((root, query, criteriaBuilder) ->
					criteriaBuilder.equal(root.get("contentTypeId"), type));
		}

		if (StringUtils.hasText(keyword)) {
			spec = spec.and((root, query, criteriaBuilder) ->
					criteriaBuilder.like(root.get("title"), "%" + keyword + "%"));
		}

		return attractionInfoRepository.findAll(spec);
	}

	@Override
	public List<AttractionInfo> searchAttractions(String keyword, int contentTypeId) {
		return attractionInfoRepository.findTop8ByKeywordAndContentTypeIdOrderByReadcountDesc(keyword, contentTypeId);
	}

	@Override
	public List<AttractionInfo> getAttractionListTopTen(String sidoCode, String gugunCode, String type, String keyword) {
		Specification<AttractionInfo> spec = Specification.where(null);

		if (StringUtils.hasText(sidoCode)) {
			spec = spec.and((root, query, criteriaBuilder) ->
					criteriaBuilder.equal(root.get("sidoCode"), sidoCode));
		}

		if (StringUtils.hasText(gugunCode)) {
			spec = spec.and((root, query, criteriaBuilder) ->
					criteriaBuilder.equal(root.get("gugunCode"), gugunCode));
		}

		if (StringUtils.hasText(type)) {
			spec = spec.and((root, query, criteriaBuilder) ->
					criteriaBuilder.equal(root.get("contentTypeId"), type));
		}

		if (StringUtils.hasText(keyword)) {
			spec = spec.and((root, query, criteriaBuilder) ->
					criteriaBuilder.like(root.get("title"), "%" + keyword + "%"));
		}

		Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "readcount")); // "readCount"를 기준으로 내림차순 정렬
		Page<AttractionInfo> pageResult = attractionInfoRepository.findAll(spec, pageable);
		return pageResult.getContent();
	}

	@Override
	public AttractionInfo getAttractionById(Integer id) {
		return attractionInfoRepository.findById(id).orElse(null);
	}

}
