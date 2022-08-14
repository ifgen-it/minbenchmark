package ru.vk.competition.minbenchmark.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class TableQueryDto {
    Integer queryId;
    String tableName;
    String query;
}
