package ru.vk.competition.minbenchmark.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.TableDto;
import ru.vk.competition.minbenchmark.service.TableService;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/table")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TableController {
    static Integer CODE_200 = 200;
    static Integer CODE_201 = 201;
    static Integer CODE_406 = 406;

    private final TableService tableService;

    @PostMapping("/create-table")
    public ResponseEntity<?> createTable(@RequestBody TableDto tableDto) {
        try {
            tableService.createTable(tableDto);
            return ResponseEntity.status(CODE_201).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_406).build();
        }
    }

    @GetMapping("/get-table-by-name/{name}")
    public ResponseEntity<TableDto> getTableStructure(@PathVariable(name = "name") String tableName) {
        try {
            Optional<TableDto> tableOpt = tableService.getTableStructure(tableName);
            if (tableOpt.isEmpty())
                return ResponseEntity.status(CODE_200).build();
            else
                return ResponseEntity.status(CODE_200).body(tableOpt.get());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_406).build();
        }
    }

    @DeleteMapping("/drop-table/{name}")
    public ResponseEntity<?> deleteTable(@PathVariable(name = "name") String tableName) {
        try {
            Optional<Boolean> resultOpt = tableService.deleteTable(tableName);
            if (resultOpt.isPresent() && Boolean.TRUE.equals(resultOpt.get()))
                return ResponseEntity.status(CODE_201).build();
            else
                return ResponseEntity.status(CODE_406).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(CODE_406).build();
        }
    }
}
