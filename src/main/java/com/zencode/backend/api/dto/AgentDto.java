package com.zencode.backend.api.dto;

import com.zencode.backend.domain.agent.AgentRole;

/**
 * 智能体信息 DTO：用于对外暴露智能体基础信息。
 *
 * @param id 智能体 ID
 * @param name 名称
 * @param blueprintId 蓝图 ID
 * @param role 角色枚举
 * @param roleDisplay 角色展示名称
 * @param enabled 是否启用
 * @param description 描述信息
 * @param emoji 表情符号
 */
public record AgentDto(String id,
                       String name,
                       String blueprintId,
                       AgentRole role,
                       String roleDisplay,
                       Boolean enabled,
                       String description,
                       String emoji) {
}