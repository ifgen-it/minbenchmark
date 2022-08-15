package ru.vk.competition.minbenchmark.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vk.competition.minbenchmark.dto.SingleQueryDto;
import ru.vk.competition.minbenchmark.entity.SingleQueryEntity;

import java.util.List;

@Mapper
public interface SingleQueryMapper {

    @Mapping(target = "id", source = "queryId")
    SingleQueryEntity toEntity(SingleQueryDto source);

    @Mapping(target = "queryId", source = "id")
    SingleQueryDto toDto(SingleQueryEntity source);
    List<SingleQueryDto> toDto(List<SingleQueryEntity> source);
}
