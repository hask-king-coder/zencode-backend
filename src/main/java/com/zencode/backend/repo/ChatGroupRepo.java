package com.zencode.backend.repo;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zencode.backend.api.dto.ChatGroupDto;
import com.zencode.backend.mapper.ChatGroupMapper;
import com.zencode.backend.domain.agent.AgentEntity;
import com.zencode.backend.domain.group.ChatGroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatGroupRepo extends ServiceImpl<ChatGroupMapper, ChatGroupEntity> {

    private final AgentRepo agentRepo;

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
                agentRepo.toDto(analyst),
                agentRepo.toDto(trader),
                agentRepo.toDto(review),
                signalAgents.stream().map(agentRepo::toDto).toList()
        );
    }
}