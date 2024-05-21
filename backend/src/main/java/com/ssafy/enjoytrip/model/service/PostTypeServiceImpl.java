package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.PostType;
import com.ssafy.enjoytrip.model.repository.PostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostTypeServiceImpl implements PostTypeService {

    @Autowired
    private PostTypeRepository postTypeRepository;

    @Override
    public PostType getPostTypeById(int postTypeId) {
        System.out.println(postTypeId);
        return postTypeRepository.findById(postTypeId).orElse(null);
    }
}
