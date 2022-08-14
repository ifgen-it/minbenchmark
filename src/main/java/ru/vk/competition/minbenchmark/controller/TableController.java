package ru.vk.competition.minbenchmark.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    static Integer OK = 201;
    static Integer FAIL = 406;

    private final TableService tableService;

    @PostMapping("/create-table")
    public ResponseEntity<Integer> createTable(@RequestBody TableDto tableDto) {
        try {
            tableService.createTable(tableDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(FAIL).body(FAIL);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(OK);
    }

    @GetMapping("/get-table-by-name/{name}")
    public ResponseEntity<TableDto> getTableStructure(@PathVariable(name = "name") String tableName) {
        try {
            Optional<TableDto> tableOpt = tableService.getTableStructure(tableName);
            if (tableOpt.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).build();
            else
                return ResponseEntity.status(HttpStatus.OK).body(tableOpt.get());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(FAIL).build();
        }
    }
}
