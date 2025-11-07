package com.zencode.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zencode.backend.api.dto.AgentDto;
import com.zencode.backend.repo.AgentRepo;
import com.zencode.backend.common.exception.ResourceNotFoundException;
import com.zencode.backend.domain.agent.AgentEntity;
import com.zencode.backend.domain.agent.AgentRole;
import com.zencode.backend.mapper.AgentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 智能体管理服务：负责智能体的查询、状态变更等核心能力。
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgentService {

    private final AgentMapper agentMapper;
    private final AgentRepo agentRepo;

    /**
     * 查询所有智能体信息。
     *
     * @return 智能体 DTO 列表
     */
    public List<AgentDto> findAll() {
        return agentMapper.selectList(new QueryWrapper<>()).stream()
                .map(agentRepo::toDto)
                .toList();
    }

    /**
     * 按角色筛选智能体。
     *
     * @param role 智能体角色
     * @return 对应角色的智能体列表
     */
    public List<AgentDto> findByRole(AgentRole role) {
        QueryWrapper<AgentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", role);
        return agentMapper.selectList(queryWrapper).stream()
                .map(agentRepo::toDto)
                .toList();
    }

    /**
     * 更新智能体启用状态。
     *
     * @param agentId 智能体 ID
     * @param enabled 启用状态
     * @return 更新后的智能体 DTO
     */
    @Transactional
    public AgentDto updateState(String agentId, boolean enabled) {
        var entity = agentMapper.selectById(agentId);
        if (entity == null) {
            throw new ResourceNotFoundException("未找到指定的智能体: " + agentId);
        }
        entity.setEnabled(enabled);
        agentMapper.updateById(entity);
        return agentRepo.toDto(entity);
    }

    /**
     * 根据 ID 获取智能体实体。
     *
     * @param id 智能体 ID
     * @return 智能体实体
     */
    public AgentEntity getEntity(String id) {
        AgentEntity entity = agentMapper.selectById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("未找到指定的智能体: " + id);
        }
        return entity;
    }

    /**
     * 将多个智能体实体转换为 Map 形式，便于快速查找。
     *
     * @param ids 智能体 ID 集合
     * @return 智能体实体 Map
     */
    public Map<String, AgentEntity> getEntityMap(Set<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return Map.of();
        }
        
        QueryWrapper<AgentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return agentMapper.selectList(queryWrapper).stream()
                .collect(Collectors.toMap(AgentEntity::getId, entity -> entity));
    }

    /**
     * 批量获取智能体实体。
     *
     * @param ids 智能体 ID 集合
     * @return 智能体实体列表
     */
    public List<AgentEntity> getEntities(Set<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        
        QueryWrapper<AgentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return agentMapper.selectList(queryWrapper);
    }
}