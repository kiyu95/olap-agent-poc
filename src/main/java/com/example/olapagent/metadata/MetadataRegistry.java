package com.example.olapagent.metadata;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MetadataRegistry {

    private final Map<String, String> metricMap = new HashMap<>();
    private final Map<String, String> dimensionMap = new HashMap<>();

    public MetadataRegistry() {
        metricMap.put("매출", "[Measures].[Sales Amount]");
        metricMap.put("판매금액", "[Measures].[Sales Amount]");
        metricMap.put("주문수", "[Measures].[Order Count]");

        dimensionMap.put("지역", "'DimRegion'[RegionName]");
        dimensionMap.put("상품", "'DimProduct'[ProductName]");
        dimensionMap.put("월", "'DimDate'[MonthName]");
        dimensionMap.put("연도", "'DimDate'[Year]");
    }

    public String findMetric(String keyword) {
        return metricMap.get(keyword);
    }

    public String findDimension(String keyword) {
        return dimensionMap.get(keyword);
    }
}