package com.JejuOreum.config.restTemplate;


import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class HttpRequestManager {

    private final RestTemplate restTemplate;
    private final JSONParser jsonParser;

    @Autowired
    public HttpRequestManager(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        jsonParser = new JSONParser();
    }


    public JSONObject httpRequestGet(String baseUrl, Map<String, String> urlParams) throws Exception {

        // uri setting
        String uri = this.getUri(baseUrl, urlParams);

        // Http parameter setting
        HttpEntity entity = this.getHttpParam(new HttpHeaders(), null);

        // Get Request with header
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        // Result
        JSONObject response = (JSONObject) jsonParser.parse(result.getBody());

        return response;
    }

    public JSONObject httpRequestGet(String baseUrl, HttpHeaders headers, Map<String, String> urlParams) throws Exception {

        // uri setting
        String uri = this.getUri(baseUrl, urlParams);

        // Http parameter setting
        HttpEntity entity= this.getHttpParam(headers, null);

        // Get request with header and body
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        // Result
        JSONObject response = (JSONObject) jsonParser.parse(result.getBody());

        return response;
    }

    public JSONObject httpRequestPost(String baseUrl, Map<String, String> bodyParams) throws Exception{

        // Http parameter setting
        HttpEntity entity = this.getHttpParam(new HttpHeaders(), bodyParams);

        // Post request with body
        ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, String.class);

        // Result
        JSONObject response = (JSONObject) jsonParser.parse(result.getBody());

        return response;
    }

    public JSONObject httpRequestPost(String baseUrl, HttpHeaders headers, Map<String, String> bodyParams) throws Exception{

        // Http parameter setting
        HttpEntity entity = this.getHttpParam(headers, bodyParams);

        // Post request with header and body
        ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, String.class);

        // Result
        JSONObject response = (JSONObject) jsonParser.parse(result.getBody());

        return response;
    }



    public String getUri(String baseUrl, Map<String, String> params) throws Exception {
        // uri setting
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        if(params != null){
            params.forEach((k, v)->{uriBuilder.queryParam(k,v);});
        }
        String uri = uriBuilder.encode().toUriString();

        return uri;
    }

    private HttpEntity getHttpParam(HttpHeaders headers, Map<String, String> bodyParams){

        HttpEntity entity = null;

        // Header Content-type default : APPLICATION_JSON
        if(headers.getContentType() == null){
            headers.setContentType(MediaType.APPLICATION_JSON);
        }

        // Body setting by Header
        if(bodyParams == null){
            entity = new HttpEntity<>(headers);
        } else if(headers.getContentType().equals(MediaType.APPLICATION_JSON)){
            entity = new HttpEntity<>(bodyParams, headers);
        } else if(headers.getContentType().equals(MediaType.APPLICATION_FORM_URLENCODED)){
            MultiValueMap<String, String> bodyParamsMVM = new LinkedMultiValueMap<>();
            bodyParams.forEach((k,v)->{bodyParamsMVM.add(k,v);});
            entity = new HttpEntity<>(bodyParamsMVM, headers);
        }

        return entity;
    }
}
