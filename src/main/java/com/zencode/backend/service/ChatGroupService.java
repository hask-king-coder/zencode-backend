package com.zencode.backend.service;

import com.zencode.backend.api.dto.AgentDto;
import com.zencode.backend.api.dto.ChatGroupDto;
import com.zencode.backend.repo.AgentRepo;
import com.zencode.backend.repo.ChatGroupRepo;
import com.zencode.backend.common.exception.ResourceNotFoundException;
import com.zencode.backend.domain.agent.AgentEntity;
import com.zencode.backend.domain.group.ChatGroupEntity;
import com.zencode.backend.mapper.ChatGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会话小组服务：负责小组的查询及智能体绑定信息组装。
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatGroupService {

    private final ChatGroupMapper repository;
    private final AgentService agentService;

    /**
     * 查询全部会话小组。
     *
     * @return 会话小组 DTO 列表
     */
    public List<ChatGroupDto> findAll() {
        return repository.selectList(null).stream()
                .map(this::mapToDto)
                .toList();
    }

    /**
     * 根据 ID 查询会话小组。
     *
     * @param id 会话小组 ID
     * @return 会话小组 DTO
     */
    public ChatGroupDto findById(String id) {
        ChatGroupEntity entity = repository.selectById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("未找到会话小组: " + id);
        }
        return mapToDto(entity);
    }

    /**
     * 将会话小组实体转换为展示层 DTO。
     *
     * @param entity 会话小组实体
     * @return 组装后的 DTO
     */
    private ChatGroupDto mapToDto(ChatGroupEntity entity) {
        AgentEntity analyst = entity.getAnalystAgentId() != null ? agentService.getEntity(entity.getAnalystAgentId()) : null;
        AgentEntity trader = entity.getTraderAgentId() != null ? agentService.getEntity(entity.getTraderAgentId()) : null;
        AgentEntity review = entity.getReviewAgentId() != null ? agentService.getEntity(entity.getReviewAgentId()) : null;
        List<AgentEntity> signalAgents = agentService.getEntities(entity.getSignalAgentIds());
        return toChatGroupDto(entity, analyst, trader, review, signalAgents);
    }

    public ChatGroupDto toChatGroupDto(ChatGroupEntity entity,
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
                toAgentDto(analyst),
                toAgentDto(trader),
                toAgentDto(review),
                signalAgents.stream().map(this::toAgentDto).toList()
        );
    }

    public AgentDto toAgentDto(AgentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AgentDto(
                entity.getId(),
                entity.getName(),
                entity.getBlueprintId(),
                entity.getRole(),
                entity.getRole().getDisplayName(),
                false,
                entity.getDescription(),
                entity.getEmoji()
        );
    }
}