package ru.vk.competition.minbenchmark.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "report_table")
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class ReportTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_table_id")
    Integer id;
    @Column(name = "table_name")
    String tableName;
    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REMOVE})
    List<ReportColumnEntity> columns = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "report_id")
    ReportEntity report;
}
