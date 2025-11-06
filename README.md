# ZenCode 后端服务

基于 Spring Boot 3.3 与 Spring AI 构建的现代化后端，实现与现有前端 (React + Vite) 的数据与智能能力对接。项目预置示例数据与 AI 推理服务，便于快速联调与二次开发。

## 技术栈

- Java 21
- Spring Boot 3.3.3 (Web, Actuator, Validation, Data JPA)
- Spring AI 1.0.0-M3 (OpenAI ChatModel 自动装配)
- SpringDoc OpenAPI 2.6.0 (接口文档)
- H2 In-Memory 数据库 (开发环境)
- Lombok、Jakarta Validation

## 快速上手

1. 安装 JDK 21 与 Maven 3.9+
2. 配置 OpenAI Key (若需启用真实大模型调用)

```bash
export OPENAI_API_KEY=sk-...
# 如使用代理/自建网关，可指定 OPENAI_BASE_URL
```

3. 启动服务

```bash
cd backend
mvn spring-boot:run
```

4. 访问文档与健康检查

- Swagger UI: http://localhost:8080/docs
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- Actuator Health: http://localhost:8080/actuator/health

> 若未配置 `OPENAI_API_KEY`，AI 接口会自动切换到离线模板模式，方便前端联调。

## 主要能力

- `GET /api/agents`：查询智能体列表，含角色、Blueprint ID、启用状态
- `PATCH /api/agents/{id}/state`：更新智能体启用状态
- `GET /api/catalog`：输出 API 数据源目录，覆盖行情/链上/舆情/宏观分类
- `GET /api/chat-groups`：返回多智能体工作流编排结构
- `POST /api/ai/insights`：调用 Spring AI 生成多因子研判洞察，或在离线模式下提供模板化建议

所有接口返回统一 `ApiResponse<T>` 包装结构，便于前端状态管理。

## Spring AI 集成要点

- 通过 `spring-ai-openai-spring-boot-starter` 自动装配 `ChatModel`
- `AiInsightService` 会在检测到模型未配置时回退到可预测的模板响应
- Prompts 采用系统 + 用户消息分离，支持追加指令与信号列表

可在 `application.yml` 调整默认模型 (`gpt-4.1-mini`) 与温度参数。

## 数据初始化

- `SampleDataInitializer` 注入示例智能体、数据源与工作组
- 使用固定 UUID，确保前后端可以稳定关联
- 默认 H2 内存库，可按需替换为 PostgreSQL/MySQL（更新 `application.yml` 并引入对应驱动）

## 开发规范

- 领域层按 `agent`/`catalog`/`group` 划分，DTO 与 Mapper 分离
- Controller 层只做输入校验与 Service 协调，异常统一由 `GlobalExceptionHandler` 处理
- 新增业务逻辑需补充单元测试 (`src/test/java`)，并运行 `mvn test`
- 遵循 Java 21 + Spring 官方代码规范，可通过 Spotless / Checkstyle 自定义约束（后续扩展项）

## 后续扩展建议

- 对接实际数据源，替换示例数据为持久化存储
- 引入 Spring Security + OAuth 保护敏感接口
- 使用 Spring Cloud Stream/Task 调度长期任务
- 编写更细粒度的集成测试与 Contract Test，提升发布质量

