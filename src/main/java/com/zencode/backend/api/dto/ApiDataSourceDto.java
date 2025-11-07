package com.zencode.backend.api.dto;

import com.zencode.backend.domain.agent.AgentRole;
import com.zencode.backend.domain.catalog.DataCategory;

import java.util.Set;

/**
 * API 数据源 DTO：用于对外暴露数据源信息。
 *
 * @param id 数据源 ID
 * @param slug 唯一标识符
 * @param name 名称
 * @param category 分类
 * @param provider 提供商
 * @param url 官网地址
 * @param freeTier 是否有免费额度
 * @param description 详细描述
 * @param notes 备注信息
 * @param rateLimit 速率限制
 * @param bestFor 最佳使用场景
 * @param primaryRole 主要适用角色
 * @param tags 标签集合
 * @param alsoSupports 同时支持的角色集合
 */
public record ApiDataSourceDto(String id,
                               String slug,
                               String name,
                               DataCategory category,
                               String provider,
                               String url,
                               Boolean freeTier,
                               String description,
                               String notes,
                               String rateLimit,
                               String bestFor,
                               AgentRole primaryRole,
                               Set<String> tags,
                               Set<AgentRole> alsoSupports) {
}