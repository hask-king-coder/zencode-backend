package com.zencode.backend.api.controller;

import com.zencode.backend.api.dto.AgentDto;
import com.zencode.backend.common.api.ApiResponse;
import com.zencode.backend.common.exception.BadRequestException;
import com.zencode.backend.domain.agent.AgentRole;
import com.zencode.backend.service.AgentService;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @GetMapping
    public ApiResponse<List<AgentDto>> findAll() {
        return ApiResponse.ok(agentService.findAll());
    }

    @GetMapping("/role/{role}")
    public ApiResponse<List<AgentDto>> findByRole(@PathVariable("role") String role) {
        AgentRole agentRole;
        try {
            agentRole = AgentRole.valueOf(role.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("不支持的智能体角色: " + role);
        }
        return ApiResponse.ok(agentService.findByRole(agentRole));
    }

    @PatchMapping("/{id}/state")
    public ApiResponse<AgentDto> updateState(@PathVariable("id") UUID id,
                                             @Valid @RequestBody UpdateAgentStateRequest request) {
        return ApiResponse.ok(agentService.updateState(id, request.enabled()));
    }

    public record UpdateAgentStateRequest(@NotNull(message = "enabled 不能为空") Boolean enabled) {
    }
}

