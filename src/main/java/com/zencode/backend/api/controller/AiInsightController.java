package com.zencode.backend.api.controller;

import com.zencode.backend.api.dto.AiInsightRequest;
import com.zencode.backend.api.dto.AiInsightResponse;
import com.zencode.backend.common.api.ApiResponse;
import com.zencode.backend.service.AiInsightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiInsightController {

    private final AiInsightService aiInsightService;

    @PostMapping("/insights")
    public ApiResponse<AiInsightResponse> generateInsight(@Valid @RequestBody AiInsightRequest request) {
        return ApiResponse.ok(aiInsightService.generateInsight(request));
    }
}

