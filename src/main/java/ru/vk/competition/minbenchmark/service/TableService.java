package ru.vk.competition.minbenchmark.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.competition.minbenchmark.dto.ColumnDto;
import ru.vk.competition.minbenchmark.dto.TableDto;
import ru.vk.competition.minbenchmark.repository.TableRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TableService {

    TableRepository tableRepository;

    public void createTable(TableDto table) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(getCreateTableBegin(table.getTableName()));

        List<ColumnDto> columns = table.getColumnInfos();
        for (ColumnDto column : columns) {
            queryBuilder.append(getCreateColumn(column));
            queryBuilder.append(", ");
        }
        queryBuilder.append(getCreatePrimaryKey(table.getPrimaryKey()));
        queryBuilder.append(getCreateTableEnd());

        String query = queryBuilder.toString();
        tableRepository.createTable(query);

    }

    private String getCreateTableEnd() {
        return ")";
    }

    private String getCreatePrimaryKey(String primaryKey) {
        return "primary key (" + primaryKey + ")";
    }

    private String getCreateColumn(ColumnDto column) {
        return column.getTitle() + " " + column.getType();
    }

    private String getCreateTableBegin(String tableName) {
        return "create table " + tableName + " (";
    }
}
