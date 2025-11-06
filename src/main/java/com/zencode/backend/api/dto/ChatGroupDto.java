package com.zencode.backend.api.dto;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record ChatGroupDto(
        UUID id,
        String name,
        String description,
        String avatarUrl,
        String note,
        String twitterHandle,
        String telegramHandle,
        Instant createdAt,
        Set<String> members,
        AgentDto analystAgent,
        AgentDto traderAgent,
        AgentDto reviewAgent,
        List<AgentDto> signalAgents
) {
}

