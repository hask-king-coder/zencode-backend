package com.zencode.backend.api.dto;

import java.time.Instant;
import java.util.Set;

/**
 * 会话小组 DTO：用于对外暴露小组配置信息。
 *
 * @param id 小组 ID
 * @param name 名称
 * @param description 描述
 * @param avatarUrl 头像链接
 * @param note 备注
 * @param twitterHandle Twitter 账号
 * @param telegramHandle Telegram 账号
 * @param createdAt 创建时间
 * @param members 成员角色列表
 * @param analystAgent 分析师智能体
 * @param traderAgent 交易员智能体
 * @param reviewAgent 复盘智能体
 * @param signalAgents 信号智能体列表
 */
public record ChatGroupDto(String id,
                           String name,
                           String description,
                           String avatarUrl,
                           String note,
                           String twitterHandle,
                           String telegramHandle,
                           Instant createdAt,
                           Set<String> members,
                           AgentDto analystAgent,
                           AgentDto traderAgent,
                           AgentDto reviewAgent,
                           java.util.List<AgentDto> signalAgents) {
}