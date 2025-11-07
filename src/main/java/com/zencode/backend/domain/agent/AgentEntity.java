package com.zencode.backend.domain.agent;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@TableName("agents")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentEntity {

    @TableId(value = "id", type = IdType.NONE)
    private String id;

    @TableField("name")
    private String name;

    @TableField("blueprint_id")
    private String blueprintId;

    @TableField("role")
    private AgentRole role;

    @TableField("enabled")
    private Boolean enabled;

    @TableField("description")
    private String description;

    @TableField("emoji")
    private String emoji;

}

