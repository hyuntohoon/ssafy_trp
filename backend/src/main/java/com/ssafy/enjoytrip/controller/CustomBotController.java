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
    public String chat(@RequestBody String prompt){
        String defaultPromt = "당신은 여행계획를 돕은 여행 계획 도우미입니다. 아래의 여행 정보와 날씨 정보를 제공할테니, 여행 계획 설립에 도움을 주세요.";
        prompt+=defaultPromt;
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse =  template.postForObject(apiURL, request, ChatGPTResponse.class);
        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }

    @GetMapping("/autoPlan")
    public String test(@RequestParam(name = "prompt")String prompt){
        String temp = prompt;
        String defaultPromt = "'당신은 여행계획를 돕은 여행 계획 도우미입니다. 여행 컨셉 또는 여행 지역을 제공할테니 제 db에 get 요청을 url를 작성해주세요.' 제 http://http://192.168.204.102/attractions?sidoCode=1&gugunCode=1&type=1'입니다. type과  sidoCode, gugunCode 파라미터를 수정해서 작성해주세요. 다른 말은 설명도 필요 없습니다. url만 딱 주세요 + ' +\n" +
        "        '@GetMapping\\\\n\\tpublic ResponseEntity<?> getList(\\n' +\n" +
                "        '\\t\\t\\t@RequestParam(required = false) String sidoCode,\\n' +\n" +
                "        '\\t\\t\\t@RequestParam(required = false) String gugunCode,\\n' +\n" +
                "        '\\t\\t\\t@RequestParam(required = false) String type,\\n' +\n" +
                "        '\\t\\t\\t@RequestParam(required = false) String keyword) {\\n' +\n" +
                "        '\\n' +\n" +
                "        '\\t\\tSystem.out.println(sidoCode + \\' \\' + gugunCode + \\' \\' + type + \\' \\' + keyword);\\n' +\n" +
                "        '\\t\\tList<AttractionInfo> attractions = attractionService.getAttractionList(sidoCode, gugunCode, type, keyword);\\n' +\n" +
                "        '\\t\\tif (attractions != null && !attractions.isEmpty()) {\\n' +\n" +
                "        '\\t\\t\\treturn ResponseEntity.ok(attractions); // 200\\n' +\n" +
                "        '\\t\\t} else {\\n' +\n" +
                "        '\\t\\t\\treturn ResponseEntity.noContent().build(); // 204\\n' +\n" +
                "        '\\t\\t}\\n\n" +
                "        { typeName: '관광지', typeCode: 12 },\n" +
                "        { typeName: '문화시설', typeCode: 14 },\n" +
                "        { typeName: '행사', typeCode: 15 },\n" +
                "        { typeName: '여행코스', typeCode: 25 },\n" +
                "        { typeName: '레포츠', typeCode: 28 },\n" +
                "        { typeName: '숙박', typeCode: 32 },\n" +
                "        { typeName: '쇼핑', typeCode: 38 },\n" +
                "        { typeName: '음식점', typeCode: 39 }\n" +
                "        '\\t}, 1\\t서울\\n' +\n" +
                "        '2\\t인천\\n' +\n" +
                "        '3\\t대전\\n' +\n" +
                "        '4\\t대구\\n' +\n" +
                "        '5\\t광주\\n' +\n" +
                "        '6\\t부산\\n' +\n" +
                "        '7\\t울산\\n' +\n" +
                "        '8\\t세종특별자치시\\n' +\n" +
                "        '31\\t경기도\\n' +\n" +
                "        '32\\t강원도\\n' +\n" +
                "        '33\\t충청북도\\n' +\n" +
                "        '34\\t충청남도\\n' +\n" +
                "        '35\\t경상북도\\n' +\n" +
                "        '36\\t경상남도\\n' +\n" +
                "        '37\\t전라북도\\n' +\n" +
                "        '38\\t전라남도\\n' +\n" +
                "        '39\\t제주도1\\t강남구\\t1\\n' +\n" +
                "        '1\\t강화군\\t2\\n' +\n" +
                "        '1\\t대덕구\\t3\\n' +\n" +
                "        '1\\t남구\\t4\\n' +\n" +
                "        '1\\t광산구\\t5\\n' +\n" +
                "        '1\\t강서구\\t6\\n' +\n" +
                "        '1\\t중구\\t7\\n' +\n" +
                "        '1\\t세종특별자치시\\t8\\n' +\n" +
                "        '1\\t가평군\\t31\\n' +\n" +
                "        '1\\t강릉시\\t32\\n' +\n" +
                "        '1\\t괴산군\\t33\\n' +\n" +
                "        '1\\t공주시\\t34\\n' +\n" +
                "        '1\\t경산시\\t35\\n' +\n" +
                "        '1\\t거제시\\t36\\n' +\n" +
                "        '1\\t고창군\\t37\\n' +\n" +
                "        '1\\t강진군\\t38\\n' +\n" +
                "        '1\\t남제주군\\t39\\n' +\n" +
                "        '2\\t강동구\\t1\\n' +\n" +
                "        '2\\t계양구\\t2\\n' +\n" +
                "        '2\\t동구\\t3\\n' +\n" +
                "        '2\\t달서구\\t4\\n' +\n" +
                "        '2\\t남구\\t5\\n' +\n" +
                "        '2\\t금정구\\t6\\n' +\n" +
                "        '2\\t남구\\t7\\n' +\n" +
                "        '2\\t고양시\\t31\\n' +\n" +
                "        '2\\t고성군\\t32\\n' +\n" +
                "        '2\\t단양군\\t33\\n' +\n" +
                "        '2\\t금산군\\t34\\n' +\n" +
                "        '2\\t경주시\\t35\\n' +\n" +
                "        '2\\t거창군\\t36\\n' +\n" +
                "        '2\\t군산시\\t37\\n' +\n" +
                "        '2\\t고흥군\\t38\\n' +\n" +
                "        '2\\t북제주군\\t39\\n' +\n" +
                "        '3\\t강북구\\t1\\n' +\n" +
                "        '3\\t미추홀구\\t2\\n' +\n" +
                "        '3\\t서구\\t3\\n' +\n" +
                "        '3\\t달성군\\t4\\n' +\n" +
                "        '3\\t동구\\t5\\n' +\n" +
                "        '3\\t기장군\\t6\\n' +\n" +
                "        '3\\t동구\\t7\\n' +\n" +
                "        '3\\t과천시\\t31\\n' +\n" +
                "        '3\\t동해시\\t32\\n' +\n" +
                "        '3\\t보은군\\t33\\n' +\n" +
                "        '3\\t논산시\\t34\\n' +\n" +
                "        '3\\t고령군\\t35\\n' +\n" +
                "        '3\\t고성군\\t36\\n' +\n" +
                "        '3\\t김제시\\t37\\n' +\n" +
                "        '3\\t곡성군\\t38\\n' +\n" +
                "        '3\\t서귀포시\\t39\\n' +\n" +
                "        '4\\t강서구\\t1\\n' +\n" +
                "        '4\\t남동구\\t2\\n' +\n" +
                "        '4\\t유성구\\t3\\n' +\n" +
                "        '4\\t동구\\t4\\n' +\n" +
                "        '4\\t북구\\t5\\n' +\n" +
                "        '4\\t남구\\t6\\n' +\n" +
                "        '4\\t북구\\t7\\n' +\n" +
                "        '4\\t광명시\\t31\\n' +\n" +
                "        '4\\t삼척시\\t32\\n' +\n" +
                "        '4\\t영동군\\t33\\n' +\n" +
                "        '4\\t당진시\\t34\\n' +\n" +
                "        '4\\t구미시\\t35\\n' +\n" +
                "        '4\\t김해시\\t36\\n' +\n" +
                "        '4\\t남원시\\t37\\n' +\n" +
                "        '4\\t광양시\\t38\\n' +\n" +
                "        '4\\t제주시\\t39\\n' +\n" +
                "        '5\\t관악구\\t1\\n' +\n" +
                "        '5\\t동구\\t2\\n' +\n" +
                "        '5\\t중구\\t3\\n' +\n" +
                "        '5\\t북구\\t4\\n' +\n" +
                "        '5\\t서구\\t5\\n' +\n" +
                "        '5\\t동구\\t6\\n' +\n" +
                "        '5\\t울주군\\t7\\n' +\n" +
                "        '5\\t광주시\\t31\\n' +\n" +
                "        '5\\t속초시\\t32\\n' +\n" +
                "        '5\\t옥천군\\t33\\n' +\n" +
                "        '5\\t보령시\\t34\\n' +\n" +
                "        '5\\t군위군\\t35\\n' +\n" +
                "        '5\\t남해군\\t36\\n' +\n" +
                "        '5\\t무주군\\t37\\n' +\n" +
                "        '5\\t구례군\\t38\\n' +\n" +
                "        '6\\t광진구\\t1\\n' +\n" +
                "        '6\\t부평구\\t2\\n' +\n" +
                "        '6\\t서구\\t4\\n' +\n" +
                "        '6\\t동래구\\t6\\n' +\n" +
                "        '6\\t구리시\\t31\\n' +\n" +
                "        '6\\t양구군\\t32\\n' +\n" +
                "        '6\\t음성군\\t33\\n' +\n" +
                "        '6\\t부여군\\t34\\n' +\n" +
                "        '6\\t김천시\\t35\\n' +\n" +
                "        '6\\t마산시\\t36\\n' +\n" +
                "        '6\\t부안군\\t37\\n' +\n" +
                "        '6\\t나주시\\t38\\n' +\n" +
                "        '7\\t구로구\\t1\\n' +\n" +
                "        '7\\t서구\\t2\\n' +\n" +
                "        '7\\t수성구\\t4\\n' +\n" +
                "        '7\\t부산진구\\t6\\n' +\n" +
                "        '7\\t군포시\\t31\\n' +\n" +
                "        '7\\t양양군\\t32\\n' +\n" +
                "        '7\\t제천시\\t33\\n' +\n" +
                "        '7\\t서산시\\t34\\n' +\n" +
                "        '7\\t문경시\\t35\\n' +\n" +
                "        '7\\t밀양시\\t36\\n' +\n" +
                "        '7\\t순창군\\t37\\n' +\n" +
                "        '7\\t담양군\\t38\\n' +\n" +
                "        '8\\t금천구\\t1\\n' +\n" +
                "        '8\\t연수구\\t2\\n' +\n" +
                "        '8\\t중구\\t4\\n' +\n" +
                "        '8\\t북구\\t6\\n' +\n" +
                "        '8\\t김포시\\t31\\n' +\n" +
                "        '8\\t영월군\\t32\\n' +\n" +
                "        '8\\t진천군\\t33\\n' +\n" +
                "        '8\\t서천군\\t34\\n' +\n" +
                "        '8\\t봉화군\\t35\\n' +\n" +
                "        '8\\t사천시\\t36\\n' +\n" +
                "        '8\\t완주군\\t37\\n' +\n" +
                "        '8\\t목포시\\t38\\n' +\n" +
                "        '9\\t노원구\\t1\\n' +\n" +
                "        '9\\t옹진군\\t2\\n' +\n" +
                "        '9\\t사상구\\t6\\n' +\n" +
                "        '9\\t남양주시\\t31\\n' +\n" +
                "        '9\\t원주시\\t32\\n' +\n" +
                "        '9\\t청원군\\t33\\n' +\n" +
                "        '9\\t아산시\\t34\\n' +\n" +
                "        '9\\t상주시\\t35\\n' +\n" +
                "        '9\\t산청군\\t36\\n' +\n" +
                "        '9\\t익산시\\t37\\n' +\n" +
                "        '9\\t무안군\\t38\\n' +\n" +
                "        '10\\t도봉구\\t1\\n' +\n" +
                "        '10\\t중구\\t2\\n' +\n" +
                "        '10\\t사하구\\t6\\n' +\n" +
                "        '10\\t동두천시\\t31\\n' +\n" +
                "        '10\\t인제군\\t32\\n' +\n" +
                "        '10\\t청주시\\t33\\n' +\n" +
                "        '10\\t성주군\\t35\\n' +\n" +
                "        '10\\t양산시\\t36\\n' +\n" +
                "        '10\\t임실군\\t37\\n' +\n" +
                "        '10\\t보성군\\t38\\n' +\n" +
                "        '11\\t동대문구\\t1\\n' +\n" +
                "        '11\\t서구\\t6\\n' +\n" +
                "        '11\\t부천시\\t31\\n' +\n" +
                "        '11\\t정선군\\t32\\n' +\n" +
                "        '11\\t충주시\\t33\\n' +\n" +
                "        '11\\t예산군\\t34\\n' +\n" +
                "        '11\\t안동시\\t35\\n' +\n" +
                "        '11\\t장수군\\t37\\n' +\n" +
                "        '11\\t순천시\\t38\\n' +\n" +
                "        '12\\t동작구\\t1\\n' +\n" +
                "        '12\\t수영구\\t6\\n' +\n" +
                "        '12\\t성남시\\t31\\n' +\n" +
                "        '12\\t철원군\\t32\\n' +\n" +
                "        '12\\t증평군\\t33\\n' +\n" +
                "        '12\\t천안시\\t34\\n' +\n" +
                "        '12\\t영덕군\\t35\\n' +\n" +
                "        '12\\t의령군\\t36\\n' +\n" +
                "        '12\\t전주시\\t37\\n' +\n" +
                "        '12\\t신안군\\t38\\n' +\n" +
                "        '13\\t마포구\\t1\\n' +\n" +
                "        '13\\t연제구\\t6\\n' +\n" +
                "        '13\\t수원시\\t31\\n' +\n" +
                "        '13\\t춘천시\\t32\\n' +\n" +
                "        '13\\t청양군\\t34\\n' +\n" +
                "        '13\\t영양군\\t35\\n' +\n" +
                "        '13\\t진주시\\t36\\n' +\n" +
                "        '13\\t정읍시\\t37\\n' +\n" +
                "        '13\\t여수시\\t38\\n' +\n" +
                "        '14\\t서대문구\\t1\\n' +\n" +
                "        '14\\t영도구\\t6\\n' +\n" +
                "        '14\\t시흥시\\t31\\n' +\n" +
                "        '14\\t태백시\\t32\\n' +\n" +
                "        '14\\t태안군\\t34\\n' +\n" +
                "        '14\\t영주시\\t35\\n' +\n" +
                "        '14\\t진해시\\t36\\n' +\n" +
                "        '14\\t진안군\\t37\\n' +\n" +
                "        '15\\t서초구\\t1\\n' +\n" +
                "        '15\\t중구\\t6\\n' +\n" +
                "        '15\\t안산시\\t31\\n' +\n" +
                "        '15\\t평창군\\t32\\n' +\n" +
                "        '15\\t홍성군\\t34\\n' +\n" +
                "        '15\\t영천시\\t35\\n' +\n" +
                "        '15\\t창녕군\\t36\\n' +\n" +
                "        '16\\t성동구\\t1\\n' +\n" +
                "        '16\\t해운대구\\t6\\n' +\n" +
                "        '16\\t안성시\\t31\\n' +\n" +
                "        '16\\t홍천군\\t32\\n' +\n" +
                "        '16\\t계룡시\\t34\\n' +\n" +
                "        '16\\t예천군\\t35\\n' +\n" +
                "        '16\\t창원시\\t36\\n' +\n" +
                "        '16\\t영광군\\t38\\n' +\n" +
                "        '17\\t성북구\\t1\\n' +\n" +
                "        '17\\t안양시\\t31\\n' +\n" +
                "        '17\\t화천군\\t32\\n' +\n" +
                "        '17\\t울릉군\\t35\\n' +\n" +
                "        '17\\t통영시\\t36\\n' +\n" +
                "        '17\\t영암군\\t38\\n' +\n" +
                "        '18\\t송파구\\t1\\n' +\n" +
                "        '18\\t양주시\\t31\\n' +\n" +
                "        '18\\t횡성군\\t32\\n' +\n" +
                "        '18\\t울진군\\t35\\n' +\n" +
                "        '18\\t하동군\\t36\\n' +\n" +
                "        '18\\t완도군\\t38\\n' +\n" +
                "        '19\\t양천구\\t1\\n' +\n" +
                "        '19\\t양평군\\t31\\n' +\n" +
                "        '19\\t의성군\\t35\\n' +\n" +
                "        '19\\t함안군\\t36\\n' +\n" +
                "        '19\\t장성군\\t38\\n' +\n" +
                "        '20\\t영등포구\\t1\\n' +\n" +
                "        '20\\t여주시\\t31\\n' +\n" +
                "        '20\\t청도군\\t35\\n' +\n" +
                "        '20\\t함양군\\t36\\n' +\n" +
                "        '20\\t장흥군\\t38\\n' +\n" +
                "        '21\\t용산구\\t1\\n' +\n" +
                "        '21\\t연천군\\t31\\n' +\n" +
                "        '21\\t청송군\\t35\\n' +\n" +
                "        '21\\t합천군\\t36\\n' +\n" +
                "        '21\\t진도군\\t38\\n' +\n" +
                "        '22\\t은평구\\t1\\n' +\n" +
                "        '22\\t오산시\\t31\\n' +\n" +
                "        '22\\t칠곡군\\t35\\n' +\n" +
                "        '22\\t함평군\\t38\\n' +\n" +
                "        '23\\t종로구\\t1\\n' +\n" +
                "        '23\\t용인시\\t31\\n' +\n" +
                "        '23\\t포항시\\t35\\n' +\n" +
                "        '23\\t해남군\\t38\\n' +\n" +
                "        '24\\t중구\\t1\\n' +\n" +
                "        '24\\t의왕시\\t31\\n' +\n" +
                "        '24\\t화순군\\t38\\n' +\n" +
                "        '25\\t중랑구\\t1\\n' +\n" +
                "        '25\\t의정부시\\t31\\n' +\n" +
                "        '26\\t이천시\\t31\\n' +\n" +
                "        '27\\t파주시\\t31\\n' +\n" +
                "        '28\\t평택시\\t31\\n' +\n" +
                "        '29\\t포천시\\t31\\n' +\n" +
                "        '30\\t하남시\\t31\\n' +\n" +
                "        '31\\t화성시\\t31'";

        prompt+=defaultPromt;
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse =  template.postForObject(apiURL, request, ChatGPTResponse.class);
        String content = chatGPTResponse.getChoices().get(0).getMessage().getContent();

        // If the content is a URL, send a GET request to that URL
        ResponseEntity<String> response = restTemplate.getForEntity(content, String.class);

        String tempPrompt = "아래 정보를 토대로";
        tempPrompt+=temp;
        tempPrompt+="컨셉의 여행을 계획해주세요.";
        tempPrompt+= response;
        request = new ChatGPTRequest(model, tempPrompt);
        ChatGPTResponse chatGPTResponseSecond =  template.postForObject(apiURL, request, ChatGPTResponse.class);
        String contentSecond = chatGPTResponseSecond.getChoices().get(0).getMessage().getContent();
        System.out.println(contentSecond);
        return contentSecond;
    }

}