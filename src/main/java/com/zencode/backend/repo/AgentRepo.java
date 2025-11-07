package com.zencode.backend.repo;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zencode.backend.api.dto.AgentDto;
import com.zencode.backend.mapper.AgentMapper;
import com.zencode.backend.domain.agent.AgentEntity;
import org.springframework.stereotype.Component;

@Component
public class AgentRepo extends ServiceImpl<AgentMapper, AgentEntity> {

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
                entity.getEnabled(),
                entity.getDescription(),
                entity.getEmoji()
        );
    }
}