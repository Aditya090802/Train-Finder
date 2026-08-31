package com.aditya.train.service;

import com.aditya.train.entity.Train;
import com.aditya.train.repo.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    private TrainRepository trainRepository;

    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public List<Train> getAllTrains()
    {
        return trainRepository.findAll();
    }


    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }
}
