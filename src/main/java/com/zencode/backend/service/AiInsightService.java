package com.zencode.backend.service;

import com.zencode.backend.api.dto.AiInsightRequest;
import com.zencode.backend.api.dto.AiInsightResponse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class AiInsightService {

    private final ChatModel chatModel;
    private final boolean aiEnabled;

    public AiInsightService(ObjectProvider<ChatModel> chatModelProvider) {
        this.chatModel = chatModelProvider.getIfAvailable();
        this.aiEnabled = this.chatModel != null;
    }

    public AiInsightResponse generateInsight(AiInsightRequest request) {
        if (!aiEnabled) {
            return buildFallbackResponse(request);
        }

        var messages = List.of(
                new SystemMessage("你是 ZenCode 平台的多因子投研助理，请基于提供的信号生成结构化洞察。"),
                new UserMessage(buildPromptText(request))
        );

        ChatResponse response = chatModel.call(new Prompt(messages));
        var output = response.getResult().getOutput();

        String content = output.getContent();
        String model = chatModel.getClass().getSimpleName();

        return new AiInsightResponse(
                content,
                true,
                model,
                Instant.now(),
                extractHighlights(content),
                buildRecommendations(request)
        );
    }

    private String buildPromptText(AiInsightRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append("主题: ").append(request.topic()).append("\n");
        builder.append("信号: \n");
        for (int i = 0; i < request.signals().size(); i++) {
            builder.append(i + 1).append(". ").append(request.signals().get(i)).append("\n");
        }
        if (request.instruction() != null && !request.instruction().isBlank()) {
            builder.append("额外指令: ").append(request.instruction()).append("\n");
        }
        builder.append("请输出: \n")
                .append("- 核心结论 (简洁段落)\n")
                .append("- 支撑证据 (要点列举)\n")
                .append("- 风险与不确定性提示\n")
                .append("- 建议的下一步动作\n");
        return builder.toString();
    }

    private List<String> extractHighlights(String content) {
        var highlights = new ArrayList<String>();
        if (content == null || content.isBlank()) {
            return highlights;
        }
        for (String line : content.split("\n")) {
            String trimmed = line.trim();
            if (trimmed.startsWith("-") || trimmed.startsWith("·")) {
                highlights.add(trimmed.substring(1).trim());
            }
            if (highlights.size() >= 5) {
                break;
            }
        }
        return highlights;
    }

    private List<String> buildRecommendations(AiInsightRequest request) {
        return request.signals().stream()
                .limit(3)
                .map(signal -> "进一步评估信号: " + signal)
                .toList();
    }

    private AiInsightResponse buildFallbackResponse(AiInsightRequest request) {
        StringBuilder fallback = new StringBuilder();
        fallback.append("[离线模式] 当前未配置大模型 API Key，返回模板化洞察。\n");
        fallback.append("主题: ").append(request.topic()).append("\n\n");
        fallback.append("核心结论: 根据现有信号，建议继续收集实时行情与社交舆情以验证趋势。\n");
        fallback.append("关键信号: ");
        fallback.append(String.join(", ", request.signals()));
        fallback.append("\n风险提示: 模型未实时验证数据，请人工复核。\n");

        return new AiInsightResponse(
                fallback.toString(),
                false,
                "offline-template",
                Instant.now(),
                request.signals().stream().limit(3).toList(),
                buildRecommendations(request)
        );
    }
}

