package ru.vk.competition.minbenchmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vk.competition.minbenchmark.entity.QueryEntity;

import java.util.List;
import java.util.Optional;

public interface QueryRepository extends JpaRepository<QueryEntity, Integer> {

    @Query(nativeQuery = true,
            value = "select * from query where upper(table_name) = upper(:tableName) AND QUERY_ID = :id")
    Optional<QueryEntity> findByIdAndTableName(@Param(value = "id") Integer id,
                                               @Param(value = "tableName") String tableName);

    Optional<QueryEntity> findById(Integer id);

    @Query(nativeQuery = true,
            value = "select * from query where upper(table_name) = upper(:tableName)")
    List<QueryEntity> findByTableName(@Param(value = "tableName") String tableName);

    List<QueryEntity> findAll();
}
