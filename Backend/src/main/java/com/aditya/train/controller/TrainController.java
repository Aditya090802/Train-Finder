package com.aditya.train.controller;

import com.aditya.train.entity.Train;
import com.aditya.train.service.TrainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/trains")
public class TrainController {

    private TrainService trainService;

     public TrainController(TrainService trainService)
    {
        this.trainService = trainService;
    }
    @GetMapping
    public List<Train> getAllTrains()
    {
        return trainService.getAllTrains();
    }

    @PostMapping
    public Train addTrain(@RequestBody Train train)
    {
        return trainService.addTrain(train);
    }

}
