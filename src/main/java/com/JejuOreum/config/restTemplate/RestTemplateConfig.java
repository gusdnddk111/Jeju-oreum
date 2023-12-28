package com.JejuOreum.config.restTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(3000); // 클라이언트와 서버 사이에 커넥션 객체 생성 시 소요되는 최대 시간 (ms)
        factory.setReadTimeout(1000); // 클라이언트가 서버에 데이터 처리를 요청하고 응답받기까지 소요되는 최대 시간 (ms)

        return new BufferingClientHttpRequestFactory(factory);
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        restTemplate.getInterceptors().add(new CustomHttpRequestInterceptor()); // CustomInterceptor 추가
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());        // DefaultResponseErrorHandler 설정

        return restTemplate;
    }
}