package com.example.olapagent.infrastructure.ssas.adomd;

import com.example.olapagent.domain.model.OlapResult;
import com.example.olapagent.infrastructure.ssas.SsasQueryExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AdomdTabularExecutor implements SsasQueryExecutor {

    @Override
    public OlapResult execute(String query) {
        // 실제 POC 1차:
        // 1) 별도 .NET Worker 호출
        // 2) 결과 JSON 받아서 rows로 변환
        // 현재는 mock 처리

        return OlapResult.builder()
                .rows(List.of(
                        Map.of("RegionName", "서울", "value", 125000000),
                        Map.of("RegionName", "부산", "value", 98000000)
                ))
                .build();
    }
}