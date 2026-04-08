package com.example.olapagent.api.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class AskResponse {
    private String question;
    private String interpretedMetric;
    private List<String> interpretedDimensions;
    private String generatedQuery;
    private List<Map<String, Object>> rows;
    private String summary;
}