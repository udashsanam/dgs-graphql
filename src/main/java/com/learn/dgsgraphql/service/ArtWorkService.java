package com.learn.dgsgraphql.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class ArtWorkService {


    private final static Logger logger = LoggerFactory.getLogger(ArtWorkService.class);

    public  String generateForTitle(String title) {
        logger.info("Generating art work for {}", title);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        UUID uuid = UUID.randomUUID();
        return uuid.toString() + "-" + title.replaceAll(" ", "_");
    }
}
