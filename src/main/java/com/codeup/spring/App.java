package com.codeup.spring;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main(String[] args) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://shazam.p.rapidapi.com/auto-complete?term=kiss%20the&locale=en-US"))
                .header("x-rapidapi-key", "${rapid_api_key}")
                .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(((java.net.http.HttpResponse<?>) response).body());


    }
}
