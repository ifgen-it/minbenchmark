package ru.vk.competition.minbenchmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vk.competition.minbenchmark.entity.SingleQueryEntity;

import java.util.List;
import java.util.Optional;

public interface SingleQueryRepository extends JpaRepository<SingleQueryEntity, Integer> {

    Optional<SingleQueryEntity> findById(Integer id);

    List<SingleQueryEntity> findAll();
}
