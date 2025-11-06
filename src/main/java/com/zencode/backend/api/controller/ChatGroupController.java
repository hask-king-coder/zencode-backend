package com.zencode.backend.api.controller;

import com.zencode.backend.api.dto.ChatGroupDto;
import com.zencode.backend.common.api.ApiResponse;
import com.zencode.backend.service.ChatGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat-groups")
@RequiredArgsConstructor
public class ChatGroupController {

    private final ChatGroupService chatGroupService;

    @GetMapping
    public ApiResponse<List<ChatGroupDto>> findAll() {
        return ApiResponse.ok(chatGroupService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<ChatGroupDto> findById(@PathVariable("id") UUID id) {
        return ApiResponse.ok(chatGroupService.findById(id));
    }
}

