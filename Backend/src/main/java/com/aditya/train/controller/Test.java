package com.aditya.train.controller;

import com.aditya.train.entity.Station;
import com.aditya.train.entity.Train;
import com.aditya.train.entity.TrainSchedule;
import com.aditya.train.repo.StationRepository;
import com.aditya.train.repo.TrainRepository;
import com.aditya.train.repo.TrainScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class Test {
    @Autowired
    StationRepository stationRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    TrainScheduleRepository trainScheduleRepository;

    @GetMapping
    public void test()
    {
        Station Delhi = new Station(null ,"New Delhi","NDLS");
        Station kolkata = new Station(null ,"Kolkata","KOA");
        Station chennai = new Station(null ,"Chennai Central","CHI");
        Station ballia = new Station(null ,"Ballia","BUI");
        Station banaras = new Station(null ,"Banaras","BSBS");
        Station mumbai = new Station(null ,"Mumbai Central","MMCT");
        Station patna = new Station(null ,"Patna Junction","PNBE");
        Station lucknow = new Station(null ,"Lucknow","LKO");
        Station kanpur = new Station(null ,"Kanpur Central","CNB");
        Station prayagraj = new Station(null ,"Prayagraj Junction","PRYJ");
        Station agra = new Station(null ,"Agra Cant","AGC");
        Station jaipur = new Station(null ,"Jaipur Junction","JP");
        Station bhopal = new Station(null ,"Bhopal Junction","BPL");
        Station surat = new Station(null ,"Surat","ST");
        Station pune = new Station(null ,"Pune Junction","PUNE");

        stationRepository.saveAll(List.of(Delhi,kolkata,chennai,ballia,banaras,mumbai,patna,lucknow,kanpur,prayagraj,agra,jaipur,surat,pune,bhopal));

        Train rajdhani = new Train(null,"Rajdhani Express","12306",null);
        Train durunto = new Train(null,"Durunto Express","12260",null);
        Train shatabdi = new Train(null,"Shatabdi Express","12043",null);
        Train garibRath = new Train(null,"Garib Rath Express","12909",null);
        Train vandeBharat = new Train(null,"Vande Bharat Express","22436",null);
        Train tejas = new Train(null,"Tejas Express","82901",null);

        trainRepository.saveAll(List.of(rajdhani,durunto,shatabdi,garibRath,vandeBharat,tejas));

        TrainSchedule sc1 = new TrainSchedule(null,rajdhani,Delhi,banaras,"06:00","14:00");
        TrainSchedule sc2 = new TrainSchedule(null,durunto,chennai,kolkata,"08:00","23:00");
        TrainSchedule sc3 = new TrainSchedule(null,shatabdi,banaras,ballia,"11:00","19:00");
        TrainSchedule sc4 = new TrainSchedule(null,garibRath,Delhi,kolkata,"05:00","18:00");
        TrainSchedule sc5 = new TrainSchedule(null,vandeBharat,Delhi,chennai,"07:00","20:00");
        TrainSchedule sc6 = new TrainSchedule(null,tejas,ballia,Delhi,"09:00","17:00");
        TrainSchedule sc7 = new TrainSchedule(null,rajdhani,kolkata,chennai,"10:00","22:00");
        TrainSchedule sc8 = new TrainSchedule(null,vandeBharat,banaras,kolkata,"04:00","13:00");
        TrainSchedule sc9 = new TrainSchedule(null,garibRath,chennai,ballia,"12:00","23:30");
        TrainSchedule sc10 = new TrainSchedule( null, tejas, prayagraj, lucknow,"09:00","12:00");
        TrainSchedule sc11 = new TrainSchedule(null, rajdhani, patna, Delhi, "07:00", "18:00");
        TrainSchedule sc12 = new TrainSchedule(null, vandeBharat, mumbai, pune,"06:30","10:00");
        TrainSchedule sc13 = new TrainSchedule(null, shatabdi, agra, jaipur, "05:30", "11:00");
        TrainSchedule sc14 = new TrainSchedule(null, garibRath, bhopal, surat, "08:30", "17:00");


        trainScheduleRepository.saveAll(List.of(sc1,sc2,sc3,sc4,sc5,sc6,sc7,sc8,sc9,sc10,sc11,sc12,sc13,sc14));

        System.out.println("Data Inserted in DataBase");
    }
}
