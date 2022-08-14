package ru.vk.competition.minbenchmark.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.vk.competition.minbenchmark.dto.TableQueryDto;
import ru.vk.competition.minbenchmark.entity.QueryEntity;

import java.util.List;

@Mapper
public interface QueryMapper {

//    @Mapping(target = "tableName", source = "tableName", qualifiedByName = "toEntityTableNameResolver")
    @Mapping(target = "id", source = "queryId")
    QueryEntity toEntity(TableQueryDto source);

    @Mapping(target = "queryId", source = "id")
    TableQueryDto toDto(QueryEntity source);
    List<TableQueryDto> toDto(List<QueryEntity> source);

    /*@Named("toEntityTableNameResolver")
    static String toEntityTableNameResolver(String tableName) {
        return tableName.toUpperCase();
    }*/
}
