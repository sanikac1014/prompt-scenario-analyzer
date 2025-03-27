package com.sanikaproject.promptengineer.controller;

import com.sanikaproject.promptengineer.model.ScenarioAnalysisRequest;
import com.sanikaproject.promptengineer.model.ScenarioAnalysisResponse;
import com.sanikaproject.promptengineer.service.AiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ScenarioAnalysisController {

    @Autowired
    private AiService aiService;

    @PostMapping("/analyze-scenario")
    public ResponseEntity<ScenarioAnalysisResponse> analyzeScenario(
            @Valid @RequestBody ScenarioAnalysisRequest request) {

        ScenarioAnalysisResponse response = aiService.generateAnalysis(request);
        return ResponseEntity.ok(response);
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMap.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
