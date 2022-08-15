package ru.vk.competition.minbenchmark.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.competition.minbenchmark.dto.Field;
import ru.vk.competition.minbenchmark.dto.ReportColumnDto;
import ru.vk.competition.minbenchmark.dto.ReportDto;
import ru.vk.competition.minbenchmark.dto.ReportTableDto;
import ru.vk.competition.minbenchmark.entity.ReportColumnEntity;
import ru.vk.competition.minbenchmark.entity.ReportEntity;
import ru.vk.competition.minbenchmark.entity.ReportTableEntity;
import ru.vk.competition.minbenchmark.mapper.ReportMapper;
import ru.vk.competition.minbenchmark.repository.ReportRepository;
import ru.vk.competition.minbenchmark.repository.TableRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReportService {

    ReportRepository reportRepository;
    TableRepository tableRepository;
    ReportMapper reportMapper;

    public void createReport(ReportDto report) {
        if (!report.getTableAmount().equals(report.getTables().size()))
            throw new IllegalArgumentException("tableAmount не совпадает с кол-вом таблиц в списке tables");

        reportRepository.findById(report.getReportId()).ifPresent(
                reportEntity -> {throw new IllegalArgumentException("Отчет с таким reportId уже существует: " + report.getReportId());}
        );
        validateTables(report.getTables());

        // сохраняем репорт
        ReportEntity reportEntity = createReportEntity(report);
        reportRepository.save(reportEntity);
    }

    public ReportDto getReportById(Integer id) {
        Optional<ReportEntity> reportEntityOpt = reportRepository.findById(id);
        if (reportEntityOpt.isEmpty())
            throw new RuntimeException("Отчет с таким reportId не существует: " + id);

        ReportEntity reportEntity = reportEntityOpt.get();
        return createReportDto(reportEntity);
    }

    private ReportDto createReportDto(ReportEntity reportEntity) {
        ReportDto report = reportMapper.reportToDto(reportEntity);
        fillColumnSize(report);
        return report;
    }

    private void fillColumnSize(ReportDto report) {
        for (ReportTableDto table : report.getTables()) {
            String tableName = table.getTableName();
            if (!tableRepository.existsTable(tableName))
                throw new IllegalArgumentException("Таблица с таким именем не существует: " + tableName);
            Integer size = tableRepository.getRowCountByTable(tableName);

            for (ReportColumnDto column : table.getColumns())
                column.setSize(size.toString());
        }
    }

    private ReportEntity createReportEntity(ReportDto report) {
        ReportEntity reportEntity = reportMapper.reportToEntity(report);
        updateLinks(reportEntity);
        return reportEntity;
    }

    private void updateLinks(ReportEntity reportEntity) {
        for (ReportTableEntity table : reportEntity.getTables()) {
            table.setReport(reportEntity);
            for (ReportColumnEntity column : table.getColumns()) {
                column.setTable(table);
            }
        }
    }

    private void validateTables(List<ReportTableDto> tables) {
        for (ReportTableDto table : tables) {
            String tableName = table.getTableName();
            if (!tableRepository.existsTable(tableName)) {
                throw new IllegalArgumentException("Таблица с таким именем не существует: " + tableName);
            }
            validateColumns(tableName, table.getColumns());
        }
    }

    private void validateColumns(@NonNull String tableName, List<ReportColumnDto> columns) {
        List<Field> fields = tableRepository.getTableStructure(tableName);
        Map<String, Field> fieldByName = fields.stream()
                .collect(Collectors.toMap(field -> field.getField().toUpperCase(), Function.identity()));

        for (ReportColumnDto column : columns) {
            String columnTitle = column.getTitle().toUpperCase();
            if (!fieldByName.containsKey(columnTitle))
                throw new IllegalArgumentException("Колонки с таким именем не существует: " + column.getTitle());

            Field field = fieldByName.get(columnTitle);
            if (!field.getType().equalsIgnoreCase(column.getType()))
                throw new IllegalArgumentException("Неверный тип колонки: " + column.getType()
                        + ". В БД у колонки тип такой: " + field.getType());
        }
    }
}
