package com.JejuOreum.config.restTemplate;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class CustomHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] reqBodyByte, ClientHttpRequestExecution execution) throws IOException {

        // Request logging
        String logStr = "** [ Request ] **\n";
        logStr += "  - METHOD : " +  request.getMethod() + "\n";
        logStr += "  - URI    : " +  request.getURI() + "\n";
        logStr += "  - HEADER : " +  request.getHeaders() + "\n";
        logStr += "  - BODY   : " +  new String(reqBodyByte, StandardCharsets.UTF_8) + "\n";
        log.info(logStr);

        // Request
        ClientHttpResponse response = execution.execute(request, reqBodyByte);

        // Response validation check
        if(response == null || response.getBody() == null){
            throw new IOException("응답이 올바르지 않습니다.");
        }

        // Response logging
        InputStreamReader isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
        String responseBodyStr = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));
        responseBodyStr = responseBodyStr.length() > 1000 ? responseBodyStr.substring(0,1000) : responseBodyStr;

        logStr = "** [ Response ] **\n";
        logStr += "  - STATUS : " +  response.getStatusCode() + "\n";
        logStr += "  - HEADER : " +  response.getHeaders() + "\n";
        logStr += "  - BODY   : " +  responseBodyStr + "\n";
        log.info(logStr);

        return response;
    }
}
