package ru.vk.competition.minbenchmark.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "report_column")
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class ReportColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_column_id")
    Integer id;
    @Column(name = "title")
    String title;
    @Column(name = "type")
    String type;

    @ManyToOne
    @JoinColumn(name = "report_table_id")
    ReportTableEntity table;
}
