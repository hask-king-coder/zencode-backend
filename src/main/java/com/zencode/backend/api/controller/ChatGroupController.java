package com.zencode.backend.api.controller;

import com.zencode.backend.api.dto.ChatGroupDto;
import com.zencode.backend.common.api.ApiResponse;
import com.zencode.backend.service.ChatGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 会话小组接口：提供小组视图数据查询能力。
 */
@RestController
@RequestMapping("/api/chat-groups")
@RequiredArgsConstructor
@Tag(name = "会话小组", description = "查询多智能体协同小组配置")
public class ChatGroupController {

    private final ChatGroupService chatGroupService;

    /**
     * 查询全部会话小组。
     *
     * @return 会话小组列表
     */
    @GetMapping
    @Operation(summary = "查询所有小组", description = "返回平台配置的全部会话小组")
    public ApiResponse<List<ChatGroupDto>> findAll() {
        return ApiResponse.ok(chatGroupService.findAll());
    }

    /**
     * 根据 ID 获取会话小组详情。
     *
     * @param id 小组 ID
     * @return 会话小组详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据 ID 查询小组", description = "获取指定会话小组的详细配置")
    public ApiResponse<ChatGroupDto> findById(@PathVariable("id") String id) {
        return ApiResponse.ok(chatGroupService.findById(id));
    }
}