package com.archit.dev.gamemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.jsoup.Jsoup;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class RawgService {

    @Value("${rawg.api.key}")
    private String apiKey;

    @Value("${rawg.api.base-url}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public String fetchDescription(String gameTitle){
        try{
            String encodedTitle = URLEncoder.encode(gameTitle, StandardCharsets.UTF_8);

            // Search
            String searchUrl = baseUrl + "/games?search=" + encodedTitle + "&key=" + apiKey;
            Map searchResponse = restTemplate.getForObject(searchUrl, Map.class);

            List results = (List) searchResponse.get("results");
            if(results == null || results.isEmpty()) return "No Description available";

            Map firstGame = (Map) results.get(0);
            Integer gameId = (Integer) firstGame.get("id");

            // Fetch
            String detailsUrl = baseUrl + "/games/" + gameId + "?key=" + apiKey;
            Map detailsResponse = restTemplate.getForObject(detailsUrl, Map.class);
            String description = (String) detailsResponse.get("description_raw");
            description = Jsoup.parse(description).text();
            return description != null ? description : "No description available";

        } catch (Exception e) {
            return "No description available";
        }
    }
}
