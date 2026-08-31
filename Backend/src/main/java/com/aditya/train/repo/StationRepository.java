package com.aditya.train.repo;

import com.aditya.train.entity.Station;
import com.aditya.train.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
