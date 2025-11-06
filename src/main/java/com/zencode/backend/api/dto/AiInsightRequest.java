package com.zencode.backend.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AiInsightRequest(
        @NotBlank(message = "topic 不能为空")
        String topic,

        @NotNull(message = "signals 不能为空")
        @Size(max = 10, message = "signals 最多包含 10 条")
        List<@NotBlank(message = "signals 内的元素不能为空") String> signals,

        @Size(max = 1024, message = "instruction 过长")
        String instruction,

        @Min(value = 256, message = "maxTokens 不能小于 256")
        Integer maxTokens
) {
}

