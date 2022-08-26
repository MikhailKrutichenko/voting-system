package com.graduation.votingSystem;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@ConfigurationProperties(prefix = "voting")
@Data
public class PropertiesConfig {

    private LocalTime time;
}
