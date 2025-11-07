package com.zencode.backend.api.controller;

import com.zencode.backend.api.dto.AgentDto;
import com.zencode.backend.common.api.ApiResponse;
import com.zencode.backend.common.exception.BadRequestException;
import com.zencode.backend.domain.agent.AgentRole;
import com.zencode.backend.service.AgentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * 智能体接口：提供智能体信息查询与状态管理能力。
 */
@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
@Tag(name = "智能体管理", description = "智能体基础信息查询与状态维护接口")
public class AgentController {

    private final AgentService agentService;

    /**
     * 查询全部智能体。
     *
     * @return 智能体列表
     */
    @GetMapping
    @Operation(summary = "查询全部智能体", description = "返回所有已注册智能体的基础信息")
    public ApiResponse<List<AgentDto>> findAll() {
        return ApiResponse.ok(agentService.findAll());
    }

    /**
     * 根据角色筛选智能体。
     *
     * @param role 角色标识
     * @return 对应角色的智能体列表
     */
    @GetMapping("/role/{role}")
    @Operation(summary = "按角色查询智能体", description = "根据角色枚举过滤智能体列表")
    public ApiResponse<List<AgentDto>> findByRole(@PathVariable("role") String role) {
        AgentRole agentRole;
        try {
            agentRole = AgentRole.valueOf(role.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("不支持的智能体角色: " + role);
        }
        return ApiResponse.ok(agentService.findByRole(agentRole));
    }

    /**
     * 更新智能体启用状态。
     *
     * @param id 智能体 ID
     * @param request 状态更新请求
     * @return 更新后的智能体信息
     */
    @PatchMapping("/{id}/state")
    @Operation(summary = "更新智能体状态", description = "启用或停用指定智能体")
    public ApiResponse<AgentDto> updateState(@PathVariable("id") String id,
                                             @Valid @RequestBody UpdateAgentStateRequest request) {
        return ApiResponse.ok(agentService.updateState(id, request.enabled()));
    }

    /**
     * 智能体状态更新请求体。
     *
     * @param enabled 是否启用
     */
    public record UpdateAgentStateRequest(@NotNull(message = "enabled 不能为空") Boolean enabled) {
    }
}