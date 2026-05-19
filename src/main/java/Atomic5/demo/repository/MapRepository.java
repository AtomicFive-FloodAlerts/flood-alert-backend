package Atomic5.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Atomic5.demo.model.MapSpot;

@Repository
public interface MapRepository extends JpaRepository<MapSpot, Long> {

}