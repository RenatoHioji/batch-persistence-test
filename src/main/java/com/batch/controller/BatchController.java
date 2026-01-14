package com.batch.controller;

import com.batch.dto.BatchDTORequest;
import com.batch.entity.BatchEntity;
import com.batch.service.BatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BatchController {

    private final BatchService batchService;

    @PostMapping("v1/batch")
    ResponseEntity<BatchEntity> create(@RequestBody BatchDTORequest request){
        return ResponseEntity.status(201).body(this.batchService.create(request));
    }

    @PostMapping("v2/batch")
    ResponseEntity<Void> createBatch(@RequestBody List<BatchDTORequest> request){
        log.info("BATCH_CONTROLLER: Iniciado o processamento da request em batch: {}", LocalDateTime.now());
        this.batchService.createBatch(request);
        log.info("BATCH_CONTROLLER: Finalizado o processamento em batch ás {}",  LocalDateTime.now());
        return ResponseEntity.status(204).build();
    }
}
