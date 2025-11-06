package com.zencode.backend.api.dto;

import java.time.Instant;
import java.util.List;

public record AiInsightResponse(
        String insight,
        boolean aiGenerated,
        String model,
        Instant generatedAt,
        List<String> highlights,
        List<String> recommendations
) {
}

