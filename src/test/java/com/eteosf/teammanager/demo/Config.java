package com.eteosf.teammanager.demo;

import com.eteosf.teammanager.repository.TeamRepository;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

    //test profile for testing
    @Configuration
    @Profile("test")
    public class Config {
        private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

        @Bean
        TeamRepository getTestImpl() {
            LOGGER.info("Test Config loaded...");
            return Mockito.mock(TeamRepository.class);
        }
    }

