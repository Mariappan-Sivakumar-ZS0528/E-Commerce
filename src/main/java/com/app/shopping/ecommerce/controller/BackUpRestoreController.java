package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.services.impl.BackUpRestoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class BackUpRestoreController {
    private BackUpRestoreService backUpRestoreService;

    public BackUpRestoreController(BackUpRestoreService backUpRestoreService) {
        this.backUpRestoreService = backUpRestoreService;
    }
    @GetMapping("/backup/{entity}")
    public ResponseEntity<String > generateSql(@PathVariable String entity) {
        backUpRestoreService.fileGenerator(entity);
        return ResponseEntity.ok("Backup Created Successfully");
    }
    @GetMapping("/restore/{entity}")
    public ResponseEntity<String > runSql(@PathVariable String entity) {
        backUpRestoreService.fileRun(entity);
        return ResponseEntity.ok("Restore Completed Successfully");
    }
}
