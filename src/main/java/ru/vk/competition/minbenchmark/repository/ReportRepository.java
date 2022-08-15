package ru.vk.competition.minbenchmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vk.competition.minbenchmark.entity.ReportEntity;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {

    Optional<ReportEntity> findById(Integer id);
}
