package com.zencode.backend.bootstrap;

import com.zencode.backend.domain.agent.AgentEntity;
import com.zencode.backend.domain.agent.AgentRole;
import com.zencode.backend.domain.catalog.ApiDataSourceEntity;
import com.zencode.backend.domain.catalog.DataCategory;
import com.zencode.backend.domain.group.ChatGroupEntity;
import com.zencode.backend.mapper.AgentMapper;
import com.zencode.backend.mapper.ApiDataSourceMapper;
import com.zencode.backend.mapper.ChatGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SampleDataInitializer implements ApplicationRunner {

    private final AgentMapper agentMapper;
    private final ApiDataSourceMapper apiDataSourceMapper;
    private final ChatGroupMapper chatGroupMapper;

    private static final String SIGNAL_AGENT_ID = "11111111-1111-1111-1111-111111111111";
    private static final String ANALYST_AGENT_ID = "22222222-2222-2222-2222-222222222222";
    private static final String TRADER_AGENT_ID = "33333333-3333-3333-3333-333333333333";
    private static final String REVIEW_AGENT_ID = "44444444-4444-4444-4444-444444444444";
    private static final String SIGNAL_AGENT_DEFI_ID = "55555555-5555-5555-5555-555555555555";
    private static final String SIGNAL_AGENT_SOCIAL_ID = "66666666-6666-6666-6666-666666666666";
    private static final String GROUP_ALPHA_ID = "77777777-7777-7777-7777-777777777777";

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (agentMapper.selectCount(null) == 0) {
            seedAgents();
        }
        if (apiDataSourceMapper.selectCount(null) == 0) {
            seedDataSources();
        }
        if (chatGroupMapper.selectCount(null) == 0) {
            seedChatGroups();
        }
    }

    private void seedAgents() {
        var agents = List.of(
                AgentEntity.builder()
                        .id(SIGNAL_AGENT_ID)
                        .name("信号捕手 Alpha")
                        .blueprintId("signal.global.alpha")
                        .role(AgentRole.SIGNAL)
                        .enabled(true)
                        .description("聚合链上与行情的实时信号，适合发现热点与风险。")
                        .emoji("🚨")
                        .build(),
                AgentEntity.builder()
                        .id(ANALYST_AGENT_ID)
                        .name("宏观研判 Analyst")
                        .blueprintId("analyst.macro.core")
                        .role(AgentRole.ANALYST)
                        .enabled(true)
                        .description("整合宏观、链上与行业数据，输出结构化研报。")
                        .emoji("📊")
                        .build(),
                AgentEntity.builder()
                        .id(TRADER_AGENT_ID)
                        .name("策略执行 Trader")
                        .blueprintId("trader.execution.smart")
                        .role(AgentRole.TRADER)
                        .enabled(true)
                        .description("将策略信号转化为可执行计划，评估风险/仓位。")
                        .emoji("⚙️")
                        .build(),
                AgentEntity.builder()
                        .id(REVIEW_AGENT_ID)
                        .name("盘后复盘 Review")
                        .blueprintId("review.daily.flash")
                        .role(AgentRole.REVIEW)
                        .enabled(true)
                        .description("盘后总结盈亏与执行情况，记录改进事项。")
                        .emoji("📝")
                        .build(),
                AgentEntity.builder()
                        .id(SIGNAL_AGENT_DEFI_ID)
                        .name("DeFi 策略雷达")
                        .blueprintId("signal.defi.yield")
                        .role(AgentRole.SIGNAL)
                        .enabled(true)
                        .description("跟踪收益池 APY、TVL 与合约风险，及时推送套利机会。")
                        .emoji("💧")
                        .build(),
                AgentEntity.builder()
                        .id(SIGNAL_AGENT_SOCIAL_ID)
                        .name("社交情绪追踪")
                        .blueprintId("signal.social.sentiment")
                        .role(AgentRole.SIGNAL)
                        .enabled(true)
                        .description("监控 Twitter/Reddit 等平台的热度变化与情绪反转。")
                        .emoji("📡")
                        .build()
        );

        agents.forEach(agentMapper::insert);
    }

    private void seedDataSources() {
        var sources = List.of(
                ApiDataSourceEntity.builder()
                        .id("10101010-1010-1010-1010-101010101010")
                        .slug("defillama-protocols")
                        .name("DefiLlama Protocols")
                        .category(DataCategory.ONCHAIN)
                        .provider("DefiLlama")
                        .url("https://defillama.com/docs/api")
                        .freeTier(true)
                        .description("全量 DeFi 协议 TVL、收入与链上指标，可用于构建板块热度、跨链监控。")
                        .bestFor("链上基本面监控")
                        // .tags(modifiableSet("tvl", "revenue")) // 暂时移除集合属性
                        .primaryRole(AgentRole.SIGNAL)
                        // .alsoSupports(modifiableSet(AgentRole.ANALYST)) // 暂时移除集合属性
                        .build(),
                ApiDataSourceEntity.builder()
                        .id("20202020-2020-2020-2020-202020202020")
                        .slug("okx-market-data")
                        .name("OKX Market Data")
                        .category(DataCategory.MARKET_DATA)
                        .provider("OKX")
                        .url("https://www.okx.com/docs-v5/zh/#rest-api-market-data")
                        .freeTier(true)
                        .description("OKX 24 小时行情接口，兼容现货与永续，便于多交易所价差/流动性监控。")
                        .rateLimit("100 req/2s/IP")
                        .bestFor("多交易所行情融合")
                        // .tags(modifiableSet("crypto", "exchange")) // 暂时移除集合属性
                        .primaryRole(AgentRole.SIGNAL)
                        // .alsoSupports(modifiableSet(AgentRole.TRADER)) // 暂时移除集合属性
                        .build(),
                ApiDataSourceEntity.builder()
                        .id("30303030-3030-3030-3030-303030303030")
                        .slug("tradingeconomics")
                        .name("TradingEconomics API")
                        .category(DataCategory.MACRO)
                        .provider("TradingEconomics")
                        .url("https://docs.tradingeconomics.com/")
                        .freeTier(true)
                        .description("宏观经济指标、央行利率与经济体日历，支撑宏观因子建模。")
                        .rateLimit("1000 req/月")
                        .bestFor("宏观经济数据拉取")
                        // .tags(modifiableSet("macro", "calendar")) // 暂时移除集合属性
                        .primaryRole(AgentRole.ANALYST)
                        // .alsoSupports(modifiableSet(AgentRole.REVIEW)) // 暂时移除集合属性
                        .build(),
                ApiDataSourceEntity.builder()
                        .id("40404040-4040-4040-4040-404040404040")
                        .slug("lunarcrush")
                        .name("LunarCrush Community API")
                        .category(DataCategory.SOCIAL)
                        .provider("LunarCrush")
                        .url("https://lunarcrush.com/developers/api")
                        .freeTier(true)
                        .description("聚合社交平台热度数据，量化情绪与影响力指标。")
                        .rateLimit("30 req/min")
                        .bestFor("跨平台情绪热度")
                        // .tags(modifiableSet("social", "sentiment")) // 暂时移除集合属性
                        .primaryRole(AgentRole.SIGNAL)
                        // .alsoSupports(modifiableSet(AgentRole.ANALYST)) // 暂时移除集合属性
                        .build(),
                ApiDataSourceEntity.builder()
                        .id("50505050-5050-5050-5050-505050505050")
                        .slug("gdelt-v2")
                        .name("GDELT Global Events")
                        .category(DataCategory.NEWS)
                        .provider("GDELT Project")
                        .url("https://blog.gdeltproject.org/gdelt-2-0-our-global-world-in-realtime/")
                        .freeTier(true)
                        .description("全球新闻与事件数据库，可筛选地缘与风险事件。")
                        .bestFor("全球事件雷达")
                        // .tags(modifiableSet("macro", "risk")) // 暂时移除集合属性
                        .primaryRole(AgentRole.REVIEW)
                        // .alsoSupports(modifiableSet(AgentRole.ANALYST, AgentRole.SIGNAL)) // 暂时移除集合属性
                        .build()
        );

        sources.forEach(apiDataSourceMapper::insert);
    }

    private void seedChatGroups() {
        var group = ChatGroupEntity.builder()
                .id(GROUP_ALPHA_ID)
                .name("Alpha 多因子工作流")
                .description("结合链上、行情与舆情的日内策略工作流。")
                .avatarUrl("https://assets.zencode.ai/groups/alpha.png")
                .note("核心团队内部使用")
                .twitterHandle("ZenCodeHQ")
                .telegramHandle("ZenCodeAlpha")
                .createdAt(Instant.now())
                // .members(modifiableSet("system", "analyst", "trader", "review")) // 暂时移除集合属性
                // .signalAgentIds(modifiableSet(SIGNAL_AGENT_ID, SIGNAL_AGENT_DEFI_ID, SIGNAL_AGENT_SOCIAL_ID)) // 暂时移除集合属性
                .analystAgentId(ANALYST_AGENT_ID)
                .traderAgentId(TRADER_AGENT_ID)
                .reviewAgentId(REVIEW_AGENT_ID)
                .build();

        chatGroupMapper.insert(group);
    }

    @SafeVarargs
    private static <T> Set<T> modifiableSet(T... items) {
        return new HashSet<>(List.of(items));
    }
}