package com.zencode.backend.domain.catalog;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@TableName("api_data_sources")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDataSourceEntity {

    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @TableField("slug")
    private String slug;

    @TableField("name")
    private String name;

    @TableField("category")
    private DataCategory category;

    @TableField("provider")
    private String provider;

    @TableField("url")
    private String url;

    @TableField("free_tier")
    private Boolean freeTier;

    @TableField("description")
    private String description;

    @TableField("notes")
    private String notes;

    @TableField("rate_limit")
    private String rateLimit;

    @TableField("best_for")
    private String bestFor;

    @TableField("primary_role")
    private com.zencode.backend.domain.agent.AgentRole primaryRole;

    // 注意：MyBatis-Plus不直接支持@ElementCollection，需要特殊处理
    // 这里暂时保留字段，后续需要通过其他方式处理集合属性
    @TableField(exist = false)
    @Builder.Default
    private Set<String> tags = new HashSet<>();

    // 注意：MyBatis-Plus不直接支持@ElementCollection，需要特殊处理
    // 这里暂时保留字段，后续需要通过其他方式处理集合属性
    @TableField(exist = false)
    @Builder.Default
    private Set<com.zencode.backend.domain.agent.AgentRole> alsoSupports = new HashSet<>();
}