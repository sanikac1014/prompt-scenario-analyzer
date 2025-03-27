package com.sanikaproject.promptengineer.service;

import com.sanikaproject.promptengineer.model.ScenarioAnalysisRequest;
import com.sanikaproject.promptengineer.model.ScenarioAnalysisResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiService {

    public ScenarioAnalysisResponse generateAnalysis(ScenarioAnalysisRequest request) {

        ScenarioAnalysisResponse response = new ScenarioAnalysisResponse();
        response.setScenarioSummary("A team is building a productivity tool for remote teams with a tight budget and security requirements.");
        response.setPotentialPitfalls(List.of(
                "Lack of clear security implementation",
                "Short timeline may affect quality",
                "Limited team bandwidth",
                "Possible scope creep"
        ));
        response.setProposedStrategies(List.of(
                "Use pre-built secure components",
                "Prioritize MVP features",
                "Daily standups to maintain momentum",
                "Clearly define scope early"
        ));
        response.setRecommendedResources(List.of(
                "OWASP Secure Coding Practices",
                "Figma for quick design iteration",
                "Vercel or Netlify for fast deployment",
                "Trello or Jira for tracking"
        ));
        response.setDisclaimer("This response is generated from a mock engine.");
        return response;
    }
} 
