package com.example.olapagent.application.service;

import com.example.olapagent.domain.enumtype.IntentType;
import com.example.olapagent.domain.model.ParsedQuestion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionParserService {

    public ParsedQuestion parse(String question) {
        String metric = null;
        List<String> dimensions = new ArrayList<>();
        IntentType intentType = IntentType.SIMPLE_AGGREGATION;
        Integer topN = null;

        if (question.contains("매출")) {
            metric = "매출";
        } else if (question.contains("주문수")) {
            metric = "주문수";
        }

        if (question.contains("지역")) {
            dimensions.add("지역");
        }
        if (question.contains("상품")) {
            dimensions.add("상품");
        }
        if (question.contains("월")) {
            dimensions.add("월");
        }

        if (question.contains("상위")) {
            intentType = IntentType.TOP_N;
            topN = extractTopN(question);
        }

        return ParsedQuestion.builder()
                .originalQuestion(question)
                .intentType(intentType)
                .metric(metric)
                .dimensions(dimensions)
                .dateFilter(null)
                .topN(topN)
                .build();
    }

    private Integer extractTopN(String question) {
        if (question.contains("10")) {
            return 10;
        }
        if (question.contains("5")) {
            return 5;
        }
        return 10;
    }
}