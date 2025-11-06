package com.zencode.backend.api.mapper;

import com.zencode.backend.api.dto.ChatGroupDto;
import com.zencode.backend.domain.agent.AgentEntity;
import com.zencode.backend.domain.group.ChatGroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatGroupMapper {

    private final AgentMapper agentMapper;

    public ChatGroupDto toDto(ChatGroupEntity entity,
                              AgentEntity analyst,
                              AgentEntity trader,
                              AgentEntity review,
                              List<AgentEntity> signalAgents) {
        return new ChatGroupDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getAvatarUrl(),
                entity.getNote(),
                entity.getTwitterHandle(),
                entity.getTelegramHandle(),
                entity.getCreatedAt(),
                entity.getMembers(),
                agentMapper.toDto(analyst),
                agentMapper.toDto(trader),
                agentMapper.toDto(review),
                signalAgents.stream().map(agentMapper::toDto).toList()
        );
    }
}

