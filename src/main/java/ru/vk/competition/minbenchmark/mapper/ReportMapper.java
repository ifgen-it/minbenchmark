package ru.vk.competition.minbenchmark.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vk.competition.minbenchmark.dto.ReportColumnDto;
import ru.vk.competition.minbenchmark.dto.ReportDto;
import ru.vk.competition.minbenchmark.dto.ReportTableDto;
import ru.vk.competition.minbenchmark.entity.ReportColumnEntity;
import ru.vk.competition.minbenchmark.entity.ReportEntity;
import ru.vk.competition.minbenchmark.entity.ReportTableEntity;

import java.util.List;

@Mapper
public interface ReportMapper {

    @Mapping(target = "reportId", source = "id")
    ReportDto reportToDto(ReportEntity source);

    ReportTableDto tableToDto(ReportTableEntity source);
    List<ReportTableDto> tableToDto(List<ReportTableEntity> source);

    @Mapping(target = "size", ignore = true)
    ReportColumnDto toDto(ReportColumnEntity source);
    List<ReportColumnDto> toDto(List<ReportColumnEntity> source);


    @Mapping(target = "id", source = "reportId")
    ReportEntity reportToEntity(ReportDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "report", ignore = true)
    ReportTableEntity tableToEntity(ReportTableDto source);
    List<ReportTableEntity> tableToEntity(List<ReportTableDto> source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "table", ignore = true)
    ReportColumnEntity toEntity(ReportColumnDto source);
    List<ReportColumnEntity> toEntity(List<ReportColumnDto> source);



}
