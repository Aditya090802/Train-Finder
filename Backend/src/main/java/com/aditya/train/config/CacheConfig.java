package com.aditya.train.config;

import com.aditya.train.entity.TrainSchedule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean(name = "")
    public TrainSchedule getInstance()
    {
        return new TrainSchedule();
    }
}
