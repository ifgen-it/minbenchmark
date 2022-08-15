package ru.vk.competition.minbenchmark.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.SingleQueryDto;
import ru.vk.competition.minbenchmark.service.SingleQueryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/single-query")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SingleQueryController {
    static Integer CODE_200 = 200;
    static Integer CODE_201 = 201;
    static Integer CODE_202 = 202;
    static Integer CODE_400 = 400;
    static Integer CODE_406 = 406;
    static Integer CODE_500 = 500;

    SingleQueryService singleQueryService;

    @PostMapping("/add-new-query")
    public ResponseEntity<?> addNewSingleQuery(@RequestBody SingleQueryDto singleQuery) {
        try {
            singleQueryService.saveSingleQuery(singleQuery);
            return ResponseEntity.status(CODE_201).build();
        } catch (Exception ex) {
            log.error("Exception in addNewSingleQuery: {}", ex.getLocalizedMessage());
            return ResponseEntity.status(CODE_400).build();
        }
    }

    @PutMapping("/modify-single-query")
    public ResponseEntity<?> modifySingleQuery(@RequestBody SingleQueryDto singleQuery) {
        try {
            singleQueryService.modifySingleQuery(singleQuery);
            return ResponseEntity.status(CODE_200).build();
        } catch (Exception ex) {
            log.error("Exception in modifySingleQuery: {}", ex.getLocalizedMessage());
            return ResponseEntity.status(CODE_406).build();
        }
    }

    @DeleteMapping("/delete-single-query-by-id/{id}")
    public ResponseEntity<?> deleteSingleQuery(@PathVariable(name = "id") Integer id) {
        try {
            singleQueryService.deleteSingleQuery(id);
            return ResponseEntity.status(CODE_202).build();
        } catch (Exception ex) {
            log.error("Exception in deleteSingleQuery: {}", ex.getLocalizedMessage());
            return ResponseEntity.status(CODE_406).build();
        }
    }

    @GetMapping("/execute-single-query-by-id/{id}")
    public ResponseEntity<?> executeSingleQuery(@PathVariable(name = "id") Integer id) {
        try {
            singleQueryService.executeSingleQuery(id);
            return ResponseEntity.status(CODE_201).build();
        } catch (Exception ex) {
            log.error("Exception in executeSingleQuery: {}", ex.getLocalizedMessage());
            return ResponseEntity.status(CODE_406).build();
        }
    }

    @GetMapping("/get-single-query-by-id/{id}")
    public ResponseEntity<SingleQueryDto> getSingleQueryById(@PathVariable(name = "id") Integer id) {
        try {
            SingleQueryDto singleQuery = singleQueryService.getSingleQueryById(id);
            return ResponseEntity.status(CODE_200).body(singleQuery);
        } catch (Exception ex) {
            log.error("Exception in getSingleQueryById: {}", ex.getLocalizedMessage());
            return ResponseEntity.status(CODE_500).build();
        }
    }

    @GetMapping("/get-all-single-queries")
    public ResponseEntity<List<SingleQueryDto>> getAllSingleQueries() {
        try {
            List<SingleQueryDto> singleQueries = singleQueryService.getAllSingleQueries();
            return ResponseEntity.status(CODE_200).body(singleQueries);
        } catch (Exception ex) {
            log.error("Exception in getAllSingleQueries: {}", ex.getLocalizedMessage());
            return ResponseEntity.status(CODE_200).build();
        }
    }
}