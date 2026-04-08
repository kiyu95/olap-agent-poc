package com.example.olapagent.infrastructure.builder;

import com.example.olapagent.domain.enumtype.IntentType;
import com.example.olapagent.domain.model.ParsedQuestion;
import com.example.olapagent.metadata.MetadataRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DaxQueryBuilder implements OlapQueryBuilder {

    private final MetadataRegistry metadataRegistry;

    @Override
    public String build(ParsedQuestion parsedQuestion) {
        String metricExpression = metadataRegistry.findMetric(parsedQuestion.getMetric());

        if (parsedQuestion.getIntentType() == IntentType.TOP_N && !parsedQuestion.getDimensions().isEmpty()) {
            String dimensionExpression = metadataRegistry.findDimension(parsedQuestion.getDimensions().get(0));

            return """
                    EVALUATE
                    TOPN(
                        %d,
                        SUMMARIZECOLUMNS(
                            %s,
                            "value", %s
                        ),
                        [value], DESC
                    )
                    """.formatted(parsedQuestion.getTopN(), dimensionExpression, metricExpression);
        }

        if (!parsedQuestion.getDimensions().isEmpty()) {
            String dimensionExpression = metadataRegistry.findDimension(parsedQuestion.getDimensions().get(0));

            return """
                    EVALUATE
                    SUMMARIZECOLUMNS(
                        %s,
                        "value", %s
                    )
                    """.formatted(dimensionExpression, metricExpression);
        }

        return """
                EVALUATE
                ROW(
                    "value", %s
                )
                """.formatted(metricExpression);
    }
}