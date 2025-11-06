package com.zencode.backend.service;

import com.zencode.backend.api.dto.ChatGroupDto;
import com.zencode.backend.api.mapper.ChatGroupMapper;
import com.zencode.backend.common.exception.ResourceNotFoundException;
import com.zencode.backend.domain.agent.AgentEntity;
import com.zencode.backend.domain.group.ChatGroupEntity;
import com.zencode.backend.repository.ChatGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatGroupService {

    private final ChatGroupRepository repository;
    private final AgentService agentService;
    private final ChatGroupMapper chatGroupMapper;

    public List<ChatGroupDto> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public ChatGroupDto findById(UUID id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("未找到会话小组: " + id));
        return mapToDto(entity);
    }

    private ChatGroupDto mapToDto(ChatGroupEntity entity) {
        AgentEntity analyst = entity.getAnalystAgentId() != null ? agentService.getEntity(entity.getAnalystAgentId()) : null;
        AgentEntity trader = entity.getTraderAgentId() != null ? agentService.getEntity(entity.getTraderAgentId()) : null;
        AgentEntity review = entity.getReviewAgentId() != null ? agentService.getEntity(entity.getReviewAgentId()) : null;
        List<AgentEntity> signalAgents = agentService.getEntities(entity.getSignalAgentIds());
        return chatGroupMapper.toDto(entity, analyst, trader, review, signalAgents);
    }
}

