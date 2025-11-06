package com.zencode.backend.domain.catalog;

public enum DataCategory {
    MARKET_DATA("行情"),
    NEWS("新闻"),
    SOCIAL("社交"),
    PREDICTION_MARKET("预测市场"),
    ONCHAIN("链上数据"),
    MACRO("宏观");

    private final String displayName;

    DataCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

