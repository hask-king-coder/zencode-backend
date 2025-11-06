package com.zencode.backend.service;

import com.zencode.backend.api.dto.ApiDataSourceDto;
import com.zencode.backend.api.mapper.ApiDataSourceMapper;
import com.zencode.backend.common.exception.ResourceNotFoundException;
import com.zencode.backend.domain.catalog.DataCategory;
import com.zencode.backend.repository.ApiDataSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiCatalogService {

    private final ApiDataSourceRepository repository;
    private final ApiDataSourceMapper mapper;

    public List<ApiDataSourceDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<ApiDataSourceDto> findByCategory(DataCategory category) {
        return repository.findByCategory(category).stream()
                .map(mapper::toDto)
                .toList();
    }

    public ApiDataSourceDto findBySlug(String slug) {
        return repository.findBySlug(slug)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("未找到对应的 API 数据源: " + slug));
    }
}

