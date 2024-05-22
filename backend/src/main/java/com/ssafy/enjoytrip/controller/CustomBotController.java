package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.ChatGPTRequest;
import com.ssafy.enjoytrip.model.dto.ChatGPTResponse;
import com.ssafy.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/chat")
    public String chat(@RequestBody String prompt) {
        String defaultPromt = "당신은 여행계획를 돕은 여행 계획 도우미입니다. 아래의 여행 정보와 날씨 정보를 제공할테니, 1. 여행계획 정리 2. 여행 계획 평가 3. 여행 계획 조언을 해주세요.";
        prompt += defaultPromt;
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);
        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }

    @GetMapping("/autoPlan")
    public String test(@RequestParam(name = "prompt") String prompt) {
        String temp = prompt;
        String defaultPromt = "당신은 여행계획를 돕은 여행 계획 도우미입니다. 여행 컨셉 또는 여행 지역을 제공할테니 제 반드시 db에 get 요청 url만 작성해주세요. 다른 말은 필요 없습니다.제 http://localhost/attractions/gpt?sidoCode=1&gugunCode=1&type=1입니다. type과  sidoCode, gugunCode 파라미터를 수정해서 작성해주세요. type은 아래의 typename 정보를 사용해주세요. 다른 말은 설명도 필요 없습니다. url만 딱 주세요 "
                +
                "@GetMapping(\"/attractions/gpt\")\n" +
                "public ResponseEntity<?> forGpt(\n" +
                "    @RequestParam(required = false) String sidoCode,\n" +
                "    @RequestParam(required = false) String gugunCode,\n" +
                "    @RequestParam(required = false) String type,\n" +
                "    @RequestParam(required = false) String keyword) {\n" +
                "\n" +
                "    System.out.println(sidoCode + ' ' + gugunCode + ' ' + type + ' ' + keyword);\n" +
                "    List<AttractionInfo> attractions = attractionService.getAttractionListTopTen(sidoCode, gugunCode, type, keyword);\n"
                +
                "    if (attractions != null && !attractions.isEmpty()) {\n" +
                "        return ResponseEntity.ok(attractions); // 200\n" +
                "    } else {\n" +
                "        return ResponseEntity.noContent().build(); // 204\n" +
                "    }\n" +
                "}" +
                "{ typeName: '관광지', typeCode: 12 },\n" +
                "{ typeName: '문화시설', typeCode: 14 },\n" +
                "{ typeName: '행사', typeCode: 15 },\n" +
                "{ typeName: '여행코스', typeCode: 25 },\n" +
                "{ typeName: '레포츠', typeCode: 28 },\n" +
                "{ typeName: '숙박', typeCode: 32 },\n" +
                "{ typeName: '쇼핑', typeCode: 38 },\n" +
                "{ typeName: '음식점', typeCode: 39 }\n" +
                "1\t서울\n" +
                "6\t부산\n" +
                "39\t제주도\n" +
                "1\t강남구\t1\n" +
                "1\t강동구\t2\n" +
                "1\t강북구\t3\n" +
                "1\t강서구\t4\n" +
                "1\t관악구\t5\n" +
                "1\t광진구\t6\n" +
                "1\t구로구\t7\n" +
                "1\t금천구\t8\n" +
                "1\t노원구\t9\n" +
                "1\t도봉구\t10\n" +
                "1\t동대문구\t11\n" +
                "1\t동작구\t12\n" +
                "1\t마포구\t13\n" +
                "1\t서대문구\t14\n" +
                "1\t서초구\t15\n" +
                "1\t성동구\t16\n" +
                "1\t성북구\t17\n" +
                "1\t송파구\t18\n" +
                "1\t양천구\t19\n" +
                "1\t영등포구\t20\n" +
                "1\t용산구\t21\n" +
                "1\t은평구\t22\n" +
                "1\t종로구\t23\n" +
                "1\t중구\t24\n" +
                "1\t중랑구\t25\n" +
                "6\t강서구\t1\n" +
                "6\t금정구\t6\n" +
                "6\t남구\t7\n" +
                "6\t동구\t5\n" +
                "6\t동래구\t6\n" +
                "6\t부산진구\t6\n" +
                "6\t북구\t7\n" +
                "6\t사상구\t9\n" +
                "6\t사하구\t10\n" +
                "6\t서구\t11\n" +
                "6\t수영구\t12\n" +
                "6\t연제구\t13\n" +
                "6\t영도구\t14\n" +
                "6\t중구\t15\n" +
                "6\t해운대구\t16\n" +
                "39\t남제주군\t1\n" +
                "39\t북제주군\t2\n" +
                "39\t서귀포시\t3\n" +
                "39\t제주시\t4\n";

        prompt += defaultPromt;
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);
        String content = chatGPTResponse.getChoices().get(0).getMessage().getContent();
        System.out.println(content);
        // If the content is a URL, send a GET request to that URL
        ResponseEntity<String> response = restTemplate.getForEntity(content, String.class);

        String tempPrompt = "아래 정보를 토대로";
        tempPrompt += temp;
        tempPrompt += "컨셉의 여행 계획을 하루에 갈 수 있도록 반드시 5개의 여행지만 선택하여 다음과 같은 형식으로 JSON을 출력해. {tripPurpose:String, tripOverview:String, tripCaution:String, tripPlan:Array(contentId:Number)}. 다른 데이터나 markdown 문법을 포함시키지 않고, JSON 텍스트만 출력해 줘";
        tempPrompt += response;
        System.out.println(tempPrompt);
        request = new ChatGPTRequest(model, tempPrompt);
        ChatGPTResponse chatGPTResponseSecond = template.postForObject(apiURL, request, ChatGPTResponse.class);
        String contentSecond = chatGPTResponseSecond.getChoices().get(0).getMessage().getContent();
        System.out.println(contentSecond);
        return contentSecond;
    }

}