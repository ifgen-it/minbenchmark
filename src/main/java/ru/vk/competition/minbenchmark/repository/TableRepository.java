package ru.vk.competition.minbenchmark.repository;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TableRepository {

    JdbcTemplate jdbcTemplate;

    public void createTable(@NonNull String query) {
        jdbcTemplate.execute(query);
    }
}
