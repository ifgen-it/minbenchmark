package ru.vk.competition.minbenchmark.entity;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "single_query")
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class SingleQueryEntity {
    @Id
    @Column(name = "query_Id")
    Integer id;

    @Column(name = "query")
    String query;
}