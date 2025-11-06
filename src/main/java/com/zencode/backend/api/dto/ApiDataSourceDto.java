package com.zencode.backend.api.dto;

import com.zencode.backend.domain.agent.AgentRole;
import com.zencode.backend.domain.catalog.DataCategory;

import java.util.Set;
import java.util.UUID;

public record ApiDataSourceDto(
        UUID id,
        String slug,
        String name,
        DataCategory category,
        String categoryDisplayName,
        String provider,
        String url,
        boolean freeTier,
        String description,
        String notes,
        String rateLimit,
        String bestFor,
        Set<String> tags,
        AgentRole primaryRole,
        String primaryRoleDisplayName,
        Set<AgentRole> alsoSupports
) {
}

