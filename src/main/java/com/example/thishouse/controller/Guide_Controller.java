package com.example.thishouse.controller;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class Guide_Controller {

    @Value("${openai.api.key}")
    private String OPENAI_API_KEY;

    @PostMapping("/chat")
    public ResponseEntity<String> chatWithGPT(@RequestBody String userInput) {
        String response;

        // GPT-3.5-turbo 모델을 사용하기 위해 엔진 이름 변경
        String engine = "text-davinci-003"; // 이전에는 davinci-codex를 사용했으나 gpt-3.5-turbo로 변경

        // GPT-3 API 호출
        try {
            String url = "https://api.openai.com/v1/engines/" + engine + "/completions";
            String prompt = "User: " + userInput + "\nAI: ";
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + OPENAI_API_KEY);
            headers.set("Content-Type", "application/json");

            JSONObject requestBody = new JSONObject();
            requestBody.put("prompt", prompt);
            requestBody.put("max_tokens", 150);

            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);
            ResponseEntity<String> apiResponse = restTemplate.postForEntity(url, entity, String.class);

            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                JSONObject jsonResponse = new JSONObject(apiResponse.getBody());
                response = jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text");
            } else {
                response = "Failed to fetch response from GPT-3.5-turbo.";
            }
        } catch (Exception e) {
            response = "Error: " + e.getMessage();
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping("/guide")
    public String guide() {
        return "guide/guide";
    }
}
