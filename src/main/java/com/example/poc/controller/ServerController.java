package com.example.poc.controller;

import com.example.poc.grpc.GreeterClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@RestController
public class ServerController {
    @Autowired
    private GreeterClientImpl greeterClient;

    @GetMapping("/hello")
    public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name) {
        return "{\"message\": \"Hello " + name + "\"}";
    }

    @GetMapping("/hello-grpc")
    public String helloGrpc(@RequestParam(name="name", required=false, defaultValue="World") String name) {
        String responseMessage = greeterClient.receiveGreeting(name);
        return "{\"message from grpc\": " + responseMessage + "}";
    }

    @GetMapping("/hello-external")
    public String helloFromExternal(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            @RequestParam(name="url", required=false, defaultValue="http://localhost:8080") String urlString
    ) throws IOException {
        URL url = new URL(urlString + "/hello?name=" + name);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        InputStream responseStream = con.getInputStream();
        String result = new BufferedReader(new InputStreamReader(responseStream))
                .lines().collect(Collectors.joining("\n"));
        return "{\"external hello message\": "+ result +" }";
    }
}
