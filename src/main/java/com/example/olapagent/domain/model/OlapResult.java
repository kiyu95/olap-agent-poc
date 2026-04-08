package com.example.olapagent.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class OlapResult {
    private List<Map<String, Object>> rows;
}