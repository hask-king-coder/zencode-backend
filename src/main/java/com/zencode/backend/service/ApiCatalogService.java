package com.zencode.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zencode.backend.api.dto.ApiDataSourceDto;
import com.zencode.backend.repo.ApiDataSourceRepo;
import com.zencode.backend.common.exception.ResourceNotFoundException;
import com.zencode.backend.domain.catalog.ApiDataSourceEntity;
import com.zencode.backend.domain.catalog.DataCategory;
import com.zencode.backend.mapper.ApiDataSourceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * API 数据源目录服务：负责数据源的查询与管理。
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiCatalogService {

    private final ApiDataSourceMapper repository;
    private final ApiDataSourceRepo apiDataSourceRepo;

    /**
     * 查询全部数据源。
     *
     * @return 数据源 DTO 列表
     */
    public List<ApiDataSourceDto> findAll() {
        return repository.selectList(null).stream()
                .map(apiDataSourceRepo::toDto)
                .toList();
    }

    /**
     * 根据分类筛选数据源。
     *
     * @param category 数据源分类
     * @return 对应分类的数据源列表
     */
    public List<ApiDataSourceDto> findByCategory(DataCategory category) {
        QueryWrapper<ApiDataSourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", category);
        return repository.selectList(queryWrapper).stream()
                .map(apiDataSourceRepo::toDto)
                .toList();
    }

    /**
     * 根据 ID 获取数据源详情。
     *
     * @param id 数据源 ID
     * @return 数据源 DTO
     */
    public ApiDataSourceDto findById(String id) {
        ApiDataSourceEntity entity = repository.selectById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("未找到指定的数据源: " + id);
        }
        return apiDataSourceRepo.toDto(entity);
    }
}