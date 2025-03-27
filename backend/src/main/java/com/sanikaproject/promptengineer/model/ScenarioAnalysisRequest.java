package com.sanikaproject.promptengineer.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public class ScenarioAnalysisRequest {

    @NotBlank(message = "Scenario cannot be empty")
    @Size(min = 20, message = "Scenario must be at least 20 characters")

    private String scenario;
    private List<String> constraints;

    // Getters and Setters
    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public List<String> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<String> constraints) {
        this.constraints = constraints;
    }
}
