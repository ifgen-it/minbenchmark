package ru.vk.competition.minbenchmark.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.TableQueryDto;
import ru.vk.competition.minbenchmark.service.TableQueryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/table-query")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TableQueryController {
    static Integer CODE_200 = 200;
    static Integer CODE_201 = 201;
    static Integer CODE_202 = 202;
    static Integer CODE_406 = 406;
    static Integer CODE_500 = 500;

    TableQueryService tableQueryService;

    @PostMapping("/add-new-query-to-table")
    public ResponseEntity<?> addNewQuery(@RequestBody TableQueryDto tableQuery) {
        try {
            tableQueryService.saveQuery(tableQuery);
            return ResponseEntity.status(CODE_201).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_406).build();
        }
    }

    @PutMapping("/modify-query-in-table")
    public ResponseEntity<?> modifyQuery(@RequestBody TableQueryDto tableQuery) {
        try {
            tableQueryService.modifyQuery(tableQuery);
            return ResponseEntity.status(CODE_200).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_406).build();
        }
    }

    @DeleteMapping("/delete-table-query-by-id/{id}")
    public ResponseEntity<?> deleteQuery(@PathVariable(name = "id") Integer id) {
        try {
            tableQueryService.deleteQuery(id);
            return ResponseEntity.status(CODE_202).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_406).build();
        }
    }

    @GetMapping("/execute-table-query-by-id/{id}")
    public ResponseEntity<?> executeQuery(@PathVariable(name = "id") Integer id) {
        try {
            tableQueryService.executeQuery(id);
            return ResponseEntity.status(CODE_201).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_406).build();
        }
    }

    @GetMapping("/get-all-queries-by-table-name/{name}")
    public ResponseEntity<List<TableQueryDto>> getQueriesByTable(@PathVariable(name = "name") String tableName) {
        try {
            List<TableQueryDto> queries = tableQueryService.getQueriesByTable(tableName);
            return ResponseEntity.status(CODE_200).body(queries);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_200).build();
        }
    }

    @GetMapping("/get-table-query-by-id/{id}")
    public ResponseEntity<TableQueryDto> getQueryById(@PathVariable(name = "id") Integer id) {
        try {
            TableQueryDto query = tableQueryService.getQueryById(id);
            return ResponseEntity.status(CODE_200).body(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_500).build();
        }
    }

    @GetMapping("/get-all-table-queries")
    public ResponseEntity<List<TableQueryDto>> getAllQueries() {
        try {
            List<TableQueryDto> queries = tableQueryService.getAllQueries();
            return ResponseEntity.status(CODE_200).body(queries);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_200).build();
        }
    }
}
