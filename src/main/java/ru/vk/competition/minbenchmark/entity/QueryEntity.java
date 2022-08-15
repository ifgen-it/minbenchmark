package ru.vk.competition.minbenchmark.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "query")
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class QueryEntity {
    @Id
    @Column(name = "query_id")
    Integer id;
    @Column(name = "table_name")
    String tableName;
    @Column(name = "query")
    String query;
}
