# SQL 脚本目录

该目录存放项目所需的数据库脚本，支持 MySQL 5.7+ 数据库。

## 文件说明

- [schema.sql](file:///Users/weikui/IdeaProjects/zencode-backend/src/main/resources/sql/schema.sql): 数据库表结构定义
- [data.sql](file:///Users/weikui/IdeaProjects/zencode-backend/src/main/resources/sql/data.sql): 初始化数据

## 表结构说明

### agents 表
存储智能体(Agent)信息，每个 Agent 代表一种特定职能的 AI 角色。

字段:
- id: 主键，使用 VARCHAR(36) 存储 UUID
- name: Agent 名称
- blueprint_id: 蓝图 ID，标识 Agent 的基础模板
- role: 角色类型 (SIGNAL, ANALYST, TRADER, REVIEW)
- enabled: 是否启用
- description: 描述信息
- emoji: 表情符号，用于前端展示

### api_data_sources 表
存储可用的数据源信息，包括 API 接口、数据提供商等。

字段:
- id: 主键，使用 VARCHAR(36) 存储 UUID
- slug: 唯一标识符，用于 URL 路径
- name: 数据源名称
- category: 分类 (ONCHAIN, MARKET_DATA, MACRO, SOCIAL, NEWS)
- provider: 提供商名称
- url: 官方网站或文档地址
- free_tier: 是否有免费额度
- description: 详细描述
- notes: 备注信息
- rate_limit: 速率限制说明
- best_for: 最佳使用场景
- primary_role: 主要适用角色

### chat_groups 表
存储聊天组信息，每个聊天组代表一个完整的工作流程。

字段:
- id: 主键，使用 VARCHAR(36) 存储 UUID
- name: 组名称
- description: 描述信息
- avatar_url: 头像链接
- note: 备注
- twitter_handle: Twitter 账号
- telegram_handle: Telegram 账号
- created_at: 创建时间

### api_data_source_tags 表
存储数据源的标签信息，用于分类和检索。

字段:
- data_source_id: 关联的数据源 ID
- tag: 标签名称

### api_data_source_support_roles 表
存储数据源支持的角色信息。

字段:
- data_source_id: 关联的数据源 ID
- role: 支持的角色类型

### chat_group_members 表
存储聊天组成员信息。

字段:
- group_id: 关联的聊天组 ID
- member_role: 成员角色

### chat_group_signal_agents 表
存储聊天组使用的信号代理信息。

字段:
- group_id: 关联的聊天组 ID
- agent_id: 关联的代理 ID

## 使用方式

这些脚本会在应用程序启动时自动执行，用于初始化数据库结构和基础数据。