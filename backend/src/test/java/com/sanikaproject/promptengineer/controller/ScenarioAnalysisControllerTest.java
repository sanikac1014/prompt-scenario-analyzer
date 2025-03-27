package com.sanikaproject.promptengineer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanikaproject.promptengineer.model.ScenarioAnalysisRequest;
import com.sanikaproject.promptengineer.model.ScenarioAnalysisResponse;
import com.sanikaproject.promptengineer.service.AiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ScenarioAnalysisController.class)
public class ScenarioAnalysisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AiService aiService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAnalyzeScenario_validRequest_returnsExpectedResponse() throws Exception {
        ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
        request.setScenario("Our team has a new client project with a tight deadline and limited budget.");
        request.setConstraints(List.of("Budget: $10,000", "Deadline: 6 weeks"));

        ScenarioAnalysisResponse response = new ScenarioAnalysisResponse();
        response.setScenarioSummary("Summary text");
        response.setPotentialPitfalls(List.of("Pitfall 1", "Pitfall 2"));
        response.setProposedStrategies(List.of("Strategy 1"));
        response.setRecommendedResources(List.of("Resource 1"));
        response.setDisclaimer("Disclaimer");

        Mockito.when(aiService.generateAnalysis(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/api/analyze-scenario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scenarioSummary").value("Summary text"));
    }

    @Test
    public void testAnalyzeScenario_emptyScenario_shouldReturnBadRequest() throws Exception {
        ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
        request.setScenario("");
        request.setConstraints(List.of("Budget: $10,000"));

        mockMvc.perform(post("/api/analyze-scenario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
