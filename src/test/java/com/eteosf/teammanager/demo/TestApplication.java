package com.eteosf.teammanager.demo;

import com.eteosf.teammanager.TeamManagerApplication;
import com.eteosf.teammanager.service.DefaultTeamService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = DefaultTeamService.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = TeamManagerApplication.class)
        }
)
public class TestApplication {
    public static void main(String[] args){
        SpringApplication.run(TestApplication.class, args);
    }
}
