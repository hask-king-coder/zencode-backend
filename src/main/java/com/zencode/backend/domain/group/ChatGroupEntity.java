package com.zencode.backend.domain.group;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@TableName("chat_groups")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatGroupEntity {

    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    @TableField("avatar_url")
    private String avatarUrl;

    @TableField("note")
    private String note;

    @TableField("twitter_handle")
    private String twitterHandle;

    @TableField("telegram_handle")
    private String telegramHandle;

    @TableField("created_at")
    private Instant createdAt;

    // 注意：MyBatis-Plus不直接支持@ElementCollection，需要特殊处理
    // 这里暂时保留字段，后续需要通过其他方式处理集合属性
    @TableField(exist = false)
    @Builder.Default
    private Set<String> members = new HashSet<>();

    // 注意：MyBatis-Plus不直接支持@ElementCollection，需要特殊处理
    // 这里暂时保留字段，后续需要通过其他方式处理集合属性
    @TableField(exist = false)
    @Builder.Default
    private Set<String> signalAgentIds = new HashSet<>();

    @TableField("analyst_agent_id")
    private String analystAgentId;

    @TableField("trader_agent_id")
    private String traderAgentId;

    @TableField("review_agent_id")
    private String reviewAgentId;
}