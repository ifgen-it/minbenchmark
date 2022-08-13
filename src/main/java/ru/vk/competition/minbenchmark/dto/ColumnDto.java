package ru.vk.competition.minbenchmark.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ColumnDto {
    String title;
    String type;
}
