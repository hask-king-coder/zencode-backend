package com.zencode.backend.api.controller;

import com.zencode.backend.api.dto.ApiDataSourceDto;
import com.zencode.backend.common.api.ApiResponse;
import com.zencode.backend.common.exception.BadRequestException;
import com.zencode.backend.domain.catalog.DataCategory;
import com.zencode.backend.service.ApiCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * API 数据源目录接口：提供数据源信息查询能力。
 */
@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
@Tag(name = "数据源目录", description = "查询可用的 API 数据源信息")
public class ApiCatalogController {

    private final ApiCatalogService apiCatalogService;

    /**
     * 查询全部数据源。
     *
     * @return 数据源列表
     */
    @GetMapping
    @Operation(summary = "查询所有数据源", description = "返回平台支持的全部数据源")
    public ApiResponse<List<ApiDataSourceDto>> findAll() {
        return ApiResponse.ok(apiCatalogService.findAll());
    }

    /**
     * 根据分类筛选数据源。
     *
     * @param category 分类标识
     * @return 对应分类的数据源列表
     */
    @GetMapping("/category/{category}")
    @Operation(summary = "按分类查询数据源", description = "根据分类枚举过滤数据源列表")
    public ApiResponse<List<ApiDataSourceDto>> findByCategory(@PathVariable("category") String category) {
        DataCategory dataCategory;
        try {
            dataCategory = DataCategory.valueOf(category.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("不支持的数据源分类: " + category);
        }
        return ApiResponse.ok(apiCatalogService.findByCategory(dataCategory));
    }

    /**
     * 根据 ID 获取数据源详情。
     *
     * @param id 数据源 ID
     * @return 数据源详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据 ID 查询数据源", description = "获取指定数据源的详细信息")
    public ApiResponse<ApiDataSourceDto> findById(@PathVariable("id") String id) {
        return ApiResponse.ok(apiCatalogService.findById(id));
    }
}