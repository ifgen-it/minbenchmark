package ru.vk.competition.minbenchmark.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vk.competition.minbenchmark.dto.TableDto;
import ru.vk.competition.minbenchmark.service.TableService;

@Slf4j
@RestController
@RequestMapping("/api/table")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @PostMapping("/create-table")
    public ResponseEntity<?> createTable(@RequestBody TableDto tableDto) {
        try {
            tableService.createTable(tableDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build(); //406
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
