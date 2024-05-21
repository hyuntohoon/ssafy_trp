package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.ChatGPTRequest;
import com.ssafy.enjoytrip.model.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bot")
public class CustomBotController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "prompt")String prompt){
        String defaultPromt = "당신은 여행계획를 돕은 여행 계획 도우미입니다. 아래의 여행 정보와 날씨 정보를 제공할테니, 여행 계획 설립에 도움을 주세요.";
        prompt+=defaultPromt;
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse =  template.postForObject(apiURL, request, ChatGPTResponse.class);
        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }

}