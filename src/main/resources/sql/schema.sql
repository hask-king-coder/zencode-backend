-- 创建agents表
drop table if exists agents;
CREATE TABLE IF NOT EXISTS agents
(
    id
    VARCHAR
(
    36
) PRIMARY KEY,
    name VARCHAR
(
    255
) NOT NULL,
    blueprint_id VARCHAR
(
    255
) NOT NULL,
    role VARCHAR
(
    32
) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    description VARCHAR
(
    1024
),
    emoji VARCHAR
(
    16
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建api_data_sources表（用于存储API数据源）
drop table if exists api_data_sources;
CREATE TABLE IF NOT EXISTS api_data_sources
(
    id
    VARCHAR
(
    36
) PRIMARY KEY,
    slug VARCHAR
(
    255
) NOT NULL UNIQUE,
    name VARCHAR
(
    255
) NOT NULL,
    category VARCHAR
(
    48
) NOT NULL,
    provider VARCHAR
(
    255
) NOT NULL,
    url VARCHAR
(
    255
) NOT NULL,
    free_tier BOOLEAN NOT NULL DEFAULT FALSE,
    description VARCHAR
(
    2048
),
    notes VARCHAR
(
    1024
),
    rate_limit VARCHAR
(
    128
),
    best_for VARCHAR
(
    256
),
    primary_role VARCHAR
(
    32
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建chat_groups表（用于存储聊天组）
drop table if exists chat_groups;
CREATE TABLE IF NOT EXISTS chat_groups
(
    id
    VARCHAR
(
    36
) PRIMARY KEY,
    name VARCHAR
(
    255
) NOT NULL,
    description VARCHAR
(
    1024
),
    avatar_url VARCHAR
(
    255
),
    note VARCHAR
(
    512
),
    twitter_handle VARCHAR
(
    64
),
    telegram_handle VARCHAR
(
    64
),
    created_at DATETIME NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建api_data_source_tags表（用于存储数据源的标签）
drop table if exists api_data_source_tags;
CREATE TABLE IF NOT EXISTS api_data_source_tags
(
    data_source_id
    VARCHAR
(
    36
) NOT NULL,
    tag VARCHAR
(
    64
) NOT NULL,
    FOREIGN KEY
(
    data_source_id
) REFERENCES api_data_sources
(
    id
) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建api_data_source_support_roles表（用于存储数据源支持的角色）
drop table if exists api_data_source_support_roles;
CREATE TABLE IF NOT EXISTS api_data_source_support_roles
(
    data_source_id
    VARCHAR
(
    36
) NOT NULL,
    role VARCHAR
(
    32
) NOT NULL,
    FOREIGN KEY
(
    data_source_id
) REFERENCES api_data_sources
(
    id
) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建chat_group_members表（用于存储聊天组成员）
drop table if exists chat_group_members;
CREATE TABLE IF NOT EXISTS chat_group_members
(
    group_id
    VARCHAR
(
    36
) NOT NULL,
    member_role VARCHAR
(
    32
) NOT NULL,
    FOREIGN KEY
(
    group_id
) REFERENCES chat_groups
(
    id
) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建chat_group_signal_agents表（用于存储聊天组的信号代理）
drop table if exists chat_group_signal_agents;
CREATE TABLE IF NOT EXISTS chat_group_signal_agents
(
    group_id
    VARCHAR
(
    36
) NOT NULL,
    agent_id VARCHAR
(
    36
) NOT NULL,
    FOREIGN KEY
(
    group_id
) REFERENCES chat_groups
(
    id
) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;