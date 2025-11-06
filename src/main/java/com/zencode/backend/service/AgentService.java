package com.zencode.backend.service;

import com.zencode.backend.api.dto.AgentDto;
import com.zencode.backend.api.mapper.AgentMapper;
import com.zencode.backend.common.exception.ResourceNotFoundException;
import com.zencode.backend.domain.agent.AgentEntity;
import com.zencode.backend.domain.agent.AgentRole;
import com.zencode.backend.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgentService {

    private final AgentRepository agentRepository;
    private final AgentMapper agentMapper;

    public List<AgentDto> findAll() {
        return agentRepository.findAll().stream()
                .map(agentMapper::toDto)
                .toList();
    }

    public List<AgentDto> findByRole(AgentRole role) {
        return agentRepository.findByRole(role).stream()
                .map(agentMapper::toDto)
                .toList();
    }

    @Transactional
    public AgentDto updateState(UUID agentId, boolean enabled) {
        var entity = agentRepository.findById(agentId)
                .orElseThrow(() -> new ResourceNotFoundException("未找到指定的智能体: " + agentId));
        entity.setEnabled(enabled);
        return agentMapper.toDto(entity);
    }

    public AgentEntity getEntity(UUID id) {
        return agentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("未找到指定的智能体: " + id));
    }

    public Map<UUID, AgentEntity> getEntityMap(Set<UUID> ids) {
        return agentRepository.findAllById(ids).stream()
                .collect(Collectors.toMap(AgentEntity::getId, entity -> entity));
    }

    public List<AgentEntity> getEntities(Set<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return agentRepository.findAllById(ids);
    }
}

