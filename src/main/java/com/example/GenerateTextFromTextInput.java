package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class GenerateTextFromTextInput {
    public static void main(String[] args) throws Exception {
        String apiKey = System.getenv("GEMINI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("Missing GEMINI_API_KEY env var");
            return;
        }
        String model = System.getenv().getOrDefault("GEMINI_MODEL", "gemini-2.5-flash");
        String prompt = (args != null && args.length > 0)
                ? String.join(" ", args)
                : "Explain how AI works in a few words";

        WebClient client = WebClient.builder().build();
        ObjectMapper mapper = new ObjectMapper();

        String json = client.post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent?key="
                        + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(Map.of(
                        "contents", List.of(
                                Map.of("parts", List.of(
                                        Map.of("text", prompt))))))
                .retrieve()
                .bodyToMono(String.class)
                .block(Duration.ofSeconds(30));

        JsonNode root = mapper.readTree(json);
        String result = root.at("/candidates/0/content/parts/0/text").asText();
        System.out.println(result == null || result.isBlank() ? "(empty response)" : result.trim());
    }
}
