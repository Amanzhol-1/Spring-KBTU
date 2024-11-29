package work.financeflowtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.financeflowtracker.entity.Limit;
import work.financeflowtracker.service.LimitService;

import java.util.List;

@RestController
@RequestMapping("/api/limits")
public class LimitController {
    private final LimitService limitService;

    @Autowired
    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    // Эндпоинт для установки нового лимита
    @PostMapping("/set")
    public ResponseEntity<?> setNewLimit(@RequestBody Limit limit) {
        try {
            Limit savedLimit = limitService.setNewLimit(limit);
            return ResponseEntity.ok(savedLimit);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Эндпоинт для получения всех лимитов
    @GetMapping("/all")
    public ResponseEntity<List<Limit>> getAllLimits() {
        return ResponseEntity.ok(limitService.findAllLimits());
    }

    // Эндпоинт для получения лимитов по категории
    @GetMapping("/by-category/{category}")
    public ResponseEntity<List<Limit>> getLimitsByCategory(@PathVariable String category) {
        List<Limit> limits = limitService.getLimitsByCategory(category);
        return ResponseEntity.ok(limits);
    }
}

