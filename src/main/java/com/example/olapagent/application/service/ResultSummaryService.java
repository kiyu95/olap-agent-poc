package com.example.olapagent.application.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResultSummaryService {

    public String summarize(String question, List<Map<String, Object>> rows) {
        if (rows == null || rows.isEmpty()) {
            return "조회 결과가 없습니다.";
        }

        if (rows.size() == 1 && rows.get(0).containsKey("value")) {
            return "질문 [%s]의 조회 결과는 %s 입니다."
                    .formatted(question, rows.get(0).get("value"));
        }

        Map<String, Object> first = rows.get(0);
        return "질문 [%s] 기준 상위 결과는 %s 입니다."
                .formatted(question, first);
    }
}