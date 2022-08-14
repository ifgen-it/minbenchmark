package ru.vk.competition.minbenchmark.repository;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vk.competition.minbenchmark.dto.Field;
import ru.vk.competition.minbenchmark.utils.DbUtils;

import java.util.*;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TableRepository {

    static String ALL_TABLES = "show tables";
    static String TABLE_STRUCTURE = "show columns from :table_name";
    static String DELETE_TABLE = "DROP TABLE :table_name";

    JdbcTemplate jdbcTemplate;
    // NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /*public TableRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }*/

    public void createTable(@NonNull String query) {
        jdbcTemplate.execute(query);
    }

    public Boolean existsTable(@NonNull String tableName) {
        tableName = tableName.toUpperCase();
        Set<String> tableNames = new HashSet<>();
        jdbcTemplate.query(ALL_TABLES, (rs, rn) -> {
            String table = rs.getString("table_name");
            tableNames.add(table);
            return null;
        });
        return tableNames.contains(tableName);
    }

    public List<Field> getTableStructure(@NonNull String tableName) {
        Map<String, String> params = Map.of("table_name", tableName);
        String query = DbUtils.substituteParams(TABLE_STRUCTURE, params);
        return jdbcTemplate.query(query, (rs, rowNum) -> new Field()
                .setField(rs.getString("field"))
                .setType(rs.getString("type"))
                .setKey(rs.getString("key")));
    }

    public void deleteTable(String tableName) {
        Map<String, String> params = Map.of("table_name", tableName);
        String query = DbUtils.substituteParams(DELETE_TABLE, params);
        jdbcTemplate.execute(query);
    }

    public void executeQuery(@NonNull String query) {
        jdbcTemplate.execute(query);
    }

    // не работает подстановка параметров
    /*public List<Field> getTableStructure1(@NonNull String tableName) {
        Map<String, Object> params = Map.of("table_name", tableName);
        // SqlParameterSource namedParams = new MapSqlParameterSource().addValue("table_name", tableName);
        return namedParameterJdbcTemplate.query(TABLE_STRUCTURE, params,
                (rs, rowNum) -> new Field()
                        .setField(rs.getString("field"))
                        .setType(rs.getString("type"))
                        .setKey(rs.getString("key"))
        );
    }*/
}
