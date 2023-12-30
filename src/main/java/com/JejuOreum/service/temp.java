package com.JejuOreum.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class temp {
    private final Environment env;

    public temp(Environment env) {
        this.env = env;
    }

    @PostConstruct //의존성 주입이 이루어진 후, 수행되는 메소드 지정하는 어노테이션
    public void init(){
        String url = env.getProperty("MYSQL_PW");
        String name = env.getProperty("API_SECRET_GOOGLE");
        log.info("url = {}",url);
        log.info("name = {}",name);

    }
}