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
@Table(name = "report")
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class ReportEntity {
    @Id
    @Column(name = "report_id")
    Integer id;
    @Column(name = "table_amount")
    Integer tableAmount;
    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REMOVE})
    List<ReportTableEntity> tables = new ArrayList<>();
}
