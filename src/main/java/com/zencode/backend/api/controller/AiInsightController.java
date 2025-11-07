package com.zencode.backend.api.controller;

import com.zencode.backend.api.dto.AiInsightRequest;
import com.zencode.backend.api.dto.AiInsightResponse;
import com.zencode.backend.common.api.ApiResponse;
import com.zencode.backend.service.AiInsightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@Tag(name = "AI洞察", description = "基于AI的市场洞察生成接口")
public class AiInsightController {

    private final AiInsightService aiInsightService;

    @PostMapping("/insights")
    @Operation(summary = "生成AI洞察", description = "基于输入的市场数据生成AI洞察报告")
    public ApiResponse<AiInsightResponse> generateInsight(@Valid @RequestBody AiInsightRequest request) {
        return ApiResponse.ok(aiInsightService.generateInsight(request));
    }
}