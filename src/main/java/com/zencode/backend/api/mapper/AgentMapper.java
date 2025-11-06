package com.zencode.backend.api.mapper;

import com.zencode.backend.api.dto.AgentDto;
import com.zencode.backend.domain.agent.AgentEntity;
import org.springframework.stereotype.Component;

@Component
public class AgentMapper {

    public AgentDto toDto(AgentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AgentDto(
                entity.getId(),
                entity.getName(),
                entity.getBlueprintId(),
                entity.getRole(),
                entity.getRole().getDisplayName(),
                entity.isEnabled(),
                entity.getDescription(),
                entity.getEmoji()
        );
    }
}

