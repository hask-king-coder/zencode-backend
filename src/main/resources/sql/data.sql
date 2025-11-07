-- 插入示例agents数据
INSERT INTO agents (id, name, blueprint_id, role, enabled, description, emoji) VALUES
('11111111-1111-1111-1111-111111111111', '信号捕手 Alpha', 'signal.global.alpha', 'SIGNAL', true, '聚合链上与行情的实时信号，适合发现热点与风险。', '🚨'),
('22222222-2222-2222-2222-222222222222', '宏观研判 Analyst', 'analyst.macro.core', 'ANALYST', true, '整合宏观、链上与行业数据，输出结构化研报。', '📊'),
('33333333-3333-3333-3333-333333333333', '策略执行 Trader', 'trader.execution.smart', 'TRADER', true, '将策略信号转化为可执行计划，评估风险/仓位。', '⚙️'),
('44444444-4444-4444-4444-444444444444', '盘后复盘 Review', 'review.daily.flash', 'REVIEW', true, '盘后总结盈亏与执行情况，记录改进事项。', '📝'),
('55555555-5555-5555-5555-555555555555', 'DeFi 策略雷达', 'signal.defi.yield', 'SIGNAL', true, '跟踪收益池 APY、TVL 与合约风险，及时推送套利机会。', '💧'),
('66666666-6666-6666-6666-666666666666', '社交情绪追踪', 'signal.social.sentiment', 'SIGNAL', true, '监控 Twitter/Reddit 等平台的热度变化与情绪反转。', '📡');

-- 插入示例api_data_sources数据
INSERT INTO api_data_sources (id, slug, name, category, provider, url, free_tier, description, best_for, rate_limit) VALUES
('10101010-1010-1010-1010-101010101010', 'defillama-protocols', 'DefiLlama Protocols', 'ONCHAIN', 'DefiLlama', 'https://defillama.com/docs/api', true, '全量 DeFi 协议 TVL、收入与链上指标，可用于构建板块热度、跨链监控。', '链上基本面监控', NULL),
('20202020-2020-2020-2020-202020202020', 'okx-market-data', 'OKX Market Data', 'MARKET_DATA', 'OKX', 'https://www.okx.com/docs-v5/zh/#rest-api-market-data', true, 'OKX 24 小时行情接口，兼容现货与永续，便于多交易所价差/流动性监控。', '多交易所行情融合', '100 req/2s/IP'),
('30303030-3030-3030-3030-303030303030', 'tradingeconomics', 'TradingEconomics API', 'MACRO', 'TradingEconomics', 'https://docs.tradingeconomics.com/', true, '宏观经济指标、央行利率与经济体日历，支撑宏观因子建模。', '宏观经济数据拉取', '1000 req/月'),
('40404040-4040-4040-4040-404040404040', 'lunarcrush', 'LunarCrush Community API', 'SOCIAL', 'LunarCrush', 'https://lunarcrush.com/developers/api', true, '聚合社交平台热度数据，量化情绪与影响力指标。', '跨平台情绪热度', '30 req/min'),
('50505050-5050-5050-5050-505050505050', 'gdelt-v2', 'GDELT Global Events', 'NEWS', 'GDELT Project', 'https://blog.gdeltproject.org/gdelt-2-0-our-global-world-in-realtime/', true, '全球新闻与事件数据库，可筛选地缘与风险事件。', '全球事件雷达', NULL);

-- 插入示例chat_groups数据
INSERT INTO chat_groups (id, name, description, avatar_url, note, twitter_handle, telegram_handle, created_at) VALUES
('77777777-7777-7777-7777-777777777777', 'Alpha 多因子工作流', '结合链上、行情与舆情的日内策略工作流。', 'https://assets.zencode.ai/groups/alpha.png', '核心团队内部使用', 'ZenCodeHQ', 'ZenCodeAlpha', UTC_TIMESTAMP());

-- 插入示例api_data_source_tags数据
INSERT INTO api_data_source_tags (data_source_id, tag) VALUES
('10101010-1010-1010-1010-101010101010', 'tvl'),
('10101010-1010-1010-1010-101010101010', 'revenue'),
('20202020-2020-2020-2020-202020202020', 'crypto'),
('20202020-2020-2020-2020-202020202020', 'exchange'),
('30303030-3030-3030-3030-303030303030', 'macro'),
('30303030-3030-3030-3030-303030303030', 'calendar'),
('40404040-4040-4040-4040-404040404040', 'social'),
('40404040-4040-4040-4040-404040404040', 'sentiment'),
('50505050-5050-5050-5050-505050505050', 'macro'),
('50505050-5050-5050-5050-505050505050', 'risk');

-- 插入示例api_data_source_support_roles数据
INSERT INTO api_data_source_support_roles (data_source_id, role) VALUES
('10101010-1010-1010-1010-101010101010', 'ANALYST'),
('20202020-2020-2020-2020-202020202020', 'TRADER'),
('30303030-3030-3030-3030-303030303030', 'REVIEW'),
('40404040-4040-4040-4040-404040404040', 'ANALYST'),
('50505050-5050-5050-5050-505050505050', 'ANALYST'),
('50505050-5050-5050-5050-505050505050', 'SIGNAL');

-- 插入示例chat_group_members数据
INSERT INTO chat_group_members (group_id, member_role) VALUES
('77777777-7777-7777-7777-777777777777', 'system'),
('77777777-7777-7777-7777-777777777777', 'analyst'),
('77777777-7777-7777-7777-777777777777', 'trader'),
('77777777-7777-7777-7777-777777777777', 'review');

-- 插入示例chat_group_signal_agents数据
INSERT INTO chat_group_signal_agents (group_id, agent_id) VALUES
('77777777-7777-7777-7777-777777777777', '11111111-1111-1111-1111-111111111111'),
('77777777-7777-7777-7777-777777777777', '55555555-5555-5555-5555-555555555555'),
('77777777-7777-7777-7777-777777777777', '66666666-6666-6666-6666-666666666666');