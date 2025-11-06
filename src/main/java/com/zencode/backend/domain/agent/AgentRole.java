package com.zencode.backend.domain.agent;

public enum AgentRole {
    SIGNAL("信号"),
    ANALYST("分析"),
    TRADER("交易"),
    REVIEW("复盘");

    private final String displayName;

    AgentRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

