package Atomic5.demo.repository;

import Atomic5.demo.model.FloodReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloodReportRepository extends MongoRepository<FloodReport, String> {
}