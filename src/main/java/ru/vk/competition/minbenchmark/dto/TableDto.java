package ru.vk.competition.minbenchmark.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TableDto {
    String tableName;
    Integer columnsAmount;
    String primaryKey;
    List<ColumnDto> columnInfos;
}
