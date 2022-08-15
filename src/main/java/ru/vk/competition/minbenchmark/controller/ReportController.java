package ru.vk.competition.minbenchmark.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.ReportDto;
import ru.vk.competition.minbenchmark.service.ReportService;

@Slf4j
@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReportController {
    static Integer CODE_201 = 201;
    static Integer CODE_406 = 406;

    ReportService reportService;

    @PostMapping("/create-report")
    public ResponseEntity<?> createReport(@RequestBody ReportDto report) {
        try {
            reportService.createReport(report);
            return ResponseEntity.status(CODE_201).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_406).build();
        }
    }

    @GetMapping("/get-report-by-id/{id}")
    public ResponseEntity<ReportDto> getReport(@PathVariable(name = "id") Integer id) {
        try {
            ReportDto report = reportService.getReportById(id);
            return ResponseEntity.status(CODE_201).body(report);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_406).build();
        }
    }
}
