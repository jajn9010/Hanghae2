package com.sparta.expert_mission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ExpertMissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpertMissionApplication.class, args);
    }

}
