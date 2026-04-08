package com.example.olapagent.application.service;

import com.example.olapagent.api.response.AskResponse;
import com.example.olapagent.domain.model.OlapResult;
import com.example.olapagent.domain.model.ParsedQuestion;
import com.example.olapagent.infrastructure.builder.DaxQueryBuilder;
import com.example.olapagent.infrastructure.ssas.SsasQueryExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AskOlapService {

    private final QuestionParserService questionParserService;
    private final DaxQueryBuilder daxQueryBuilder;
    private final SsasQueryExecutor ssasQueryExecutor;
    private final ResultSummaryService resultSummaryService;

    public AskResponse ask(String question) {
        ParsedQuestion parsedQuestion = questionParserService.parse(question);
        String daxQuery = daxQueryBuilder.build(parsedQuestion);
        OlapResult result = ssasQueryExecutor.execute(daxQuery);

        return AskResponse.builder()
                .question(question)
                .interpretedMetric(parsedQuestion.getMetric())
                .interpretedDimensions(parsedQuestion.getDimensions())
                .generatedQuery(daxQuery)
                .rows(result.getRows())
                .summary(resultSummaryService.summarize(question, result.getRows()))
                .build();
    }
}