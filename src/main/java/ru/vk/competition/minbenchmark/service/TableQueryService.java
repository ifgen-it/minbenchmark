package ru.vk.competition.minbenchmark.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.competition.minbenchmark.dto.TableQueryDto;
import ru.vk.competition.minbenchmark.entity.QueryEntity;
import ru.vk.competition.minbenchmark.mapper.QueryMapper;
import ru.vk.competition.minbenchmark.repository.QueryRepository;
import ru.vk.competition.minbenchmark.repository.TableRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TableQueryService {
    TableRepository tableRepository;
    QueryRepository queryRepository;
    QueryMapper queryMapper;

    public void saveQuery(TableQueryDto tableQuery) {
        String tableName = tableQuery.getTableName();
        if (!tableRepository.existsTable(tableName)) {
            throw new IllegalArgumentException("Таблица с таким именем не существует");
        }
        QueryEntity queryEntity = queryMapper.toEntity(tableQuery);
        queryRepository.save(queryEntity);
    }

    public void modifyQuery(TableQueryDto tableQuery) {
        String tableName = tableQuery.getTableName();
        if (!tableRepository.existsTable(tableName)) {
            throw new IllegalArgumentException("Таблица с таким именем не существует");
        }

        Optional<QueryEntity> queryOpt = queryRepository
                .findByIdAndTableName(tableQuery.getQueryId(), tableQuery.getTableName());
        if (queryOpt.isPresent()) {
            QueryEntity queryEntity = queryOpt.get();
            queryEntity.setQuery(tableQuery.getQuery());
            queryRepository.save(queryEntity);
        }
        else {
            throw new IllegalArgumentException("Запроса с таким id не существует");
        }
    }

    public void deleteQuery(@NonNull Integer id) {
        Optional<QueryEntity> queryOpt = queryRepository.findById(id);
        if (queryOpt.isEmpty())
            throw new IllegalArgumentException("Запроса с таким id не существует");
        else
            queryRepository.delete(queryOpt.get());
    }

    public void executeQuery(@NonNull Integer id) {
        Optional<QueryEntity> queryOpt = queryRepository.findById(id);
        if (queryOpt.isEmpty())
            throw new IllegalArgumentException("Запроса с таким id не существует");

        try {
            tableRepository.executeQuery(queryOpt.get().getQuery());
        } catch (Exception ex) {
            throw new RuntimeException("Синтаксис запроса неверный");
        }
    }

    public List<TableQueryDto> getQueriesByTable(@NonNull String tableName) {
        if (!tableRepository.existsTable(tableName)) {
            throw new IllegalArgumentException("Таблица с таким именем не существует");
        }
        List<QueryEntity> queryEntities = queryRepository.findByTableName(tableName);
        return queryMapper.toDto(queryEntities);
    }

    public TableQueryDto getQueryById(Integer id) {
        Optional<QueryEntity> queryOpt = queryRepository.findById(id);
        if (queryOpt.isEmpty())
            throw new IllegalArgumentException("Запроса с таким id не существует");
        return queryMapper.toDto(queryOpt.get());
    }

    public List<TableQueryDto> getAllQueries() {
        List<QueryEntity> queryEntities = queryRepository.findAll();
        return queryMapper.toDto(queryEntities);
    }
}
