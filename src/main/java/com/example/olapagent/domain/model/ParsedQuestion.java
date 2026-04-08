package com.example.olapagent.domain.model;

import com.example.olapagent.domain.enumtype.IntentType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ParsedQuestion {
    private String originalQuestion;
    private IntentType intentType;
    private String metric;
    private List<String> dimensions;
    private String dateFilter;
    private Integer topN;
}