package com.aditya.train.controller;

import com.aditya.train.entity.TrainSchedule;
import com.aditya.train.service.TrainSearchService;
import com.aditya.train.service.TrainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class TrainSearchController {

    private TrainSearchService trainSearchService;

    private TrainSearchController(TrainSearchService trainSearchService) {
        this.trainSearchService = trainSearchService;
    }

    @GetMapping("/by-code")
    public List<TrainSchedule> findTrainByStationCode(@RequestParam String sourceCode, @RequestParam String destinationCode)
    {
        return trainSearchService.findTrainByStationCode(sourceCode.toUpperCase(),destinationCode.toUpperCase());
    }

    @GetMapping("/by-name")
    public List<TrainSchedule> findTrainByStationName(@RequestParam String sourceName, @RequestParam String destinationName)
    {
        return trainSearchService.findTrainByStationCode(sourceName.toUpperCase(),destinationName.toUpperCase());
    }
}
