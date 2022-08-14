package ru.vk.competition.minbenchmark.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class TableDto {
    String tableName;
    Integer columnsAmount;
    String primaryKey;
    List<ColumnDto> columnInfos = new ArrayList<>();
}
