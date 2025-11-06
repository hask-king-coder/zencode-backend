package com.zencode.backend.api.dto;

import com.zencode.backend.domain.agent.AgentRole;

import java.util.UUID;

public record AgentDto(
        UUID id,
        String name,
        String blueprintId,
        AgentRole role,
        String roleDisplayName,
        boolean enabled,
        String description,
        String emoji
) {
}

