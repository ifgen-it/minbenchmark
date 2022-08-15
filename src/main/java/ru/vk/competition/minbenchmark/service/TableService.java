package ru.vk.competition.minbenchmark.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.competition.minbenchmark.dto.ColumnDto;
import ru.vk.competition.minbenchmark.dto.Field;
import ru.vk.competition.minbenchmark.dto.TableDto;
import ru.vk.competition.minbenchmark.repository.TableRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TableService {

    TableRepository tableRepository;
    TypeMappingService typeMappingService;

    public void createTable(TableDto table) {
        if (tableRepository.existsTable(table.getTableName())) {
            throw new IllegalArgumentException("Таблица с таким именем уже существует");
        }
        if (!table.getColumnsAmount().equals(table.getColumnInfos().size())) {
            throw new IllegalArgumentException("Несоответствие количества атрибутов в таблице");
        }
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

    public void updateTypeMapping(TableDto table) {
        TableDto savedTable = getTableStructure(table.getTableName())
                .orElseThrow(() -> new RuntimeException("Ошибка в методе updateTypeMapping: таблица была сохранена, но теперь не найдена"));

        Map<String, ColumnDto> originColumnsByTitle =  table.getColumnInfos().stream()
                .collect(Collectors.toMap(column -> column.getTitle().toUpperCase(), Function.identity()));

        for (ColumnDto columnDto : savedTable.getColumnInfos()) {
            String title = columnDto.getTitle().toUpperCase();
            String dbType = columnDto.getType();
            if (originColumnsByTitle.containsKey(title)) {
                String type = originColumnsByTitle.get(title).getType();
                typeMappingService.addMapping(dbType, type);
            }
        }
    }

    public Optional<TableDto> getTableStructure(String tableName) {
        if (!tableRepository.existsTable(tableName)) {
            return Optional.empty();
        }
        List<Field> fields = tableRepository.getTableStructure(tableName);
        TableDto table = new TableDto()
                .setTableName(tableName)
                .setColumnsAmount(fields.size());

        AtomicReference<String> primaryKey = new AtomicReference<>();
        List<ColumnDto> columns = fields.stream()
                .map(field -> {
            ColumnDto column = new ColumnDto()
                    .setTitle(field.getField().toUpperCase())
                    .setType(field.getType().toUpperCase());
            if (field.getKey().trim().equals("PRI")) {
                primaryKey.set(field.getField());
            }
            return column;
        }).collect(Collectors.toList());

        table.setColumnInfos(columns)
                .setPrimaryKey(primaryKey.get().toLowerCase());

        return Optional.of(table);
    }

    public Optional<Boolean> deleteTable(String tableName) {
        if (!tableRepository.existsTable(tableName)) {
            return Optional.empty();
        }
        tableRepository.deleteTable(tableName);
        return Optional.of(true);
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
