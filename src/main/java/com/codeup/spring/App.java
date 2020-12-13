package com.codeup.spring;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.net.URLEncoder;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        // Host url
        String host = "https://shazam.p.rapidapi.com/auto-complete";
        String charset = "UTF-8";
        // Headers for a request
        String x_rapidapi_host = "shazam.p.rapidapi.com";
        String x_rapidapi_key = "a72390726amsh9dfa2ca4e208462p1b8129jsnff84f0f14396";//Type here your key
        // Params
        String s = "Pulp";
        // Format query for preventing encoding problems
        String query = String.format("s=%s",
                URLEncoder.encode(s, charset));

        HttpResponse<JsonNode> response = Unirest.get(host + "?" + query)
                .header("x-rapidapi-host", x_rapidapi_host)
                .header("x-rapidapi-key", x_rapidapi_key)
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getHeaders().get("Content-Type"));
    }
}
