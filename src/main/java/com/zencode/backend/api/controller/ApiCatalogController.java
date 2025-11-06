package com.zencode.backend.api.controller;

import com.zencode.backend.api.dto.ApiDataSourceDto;
import com.zencode.backend.common.api.ApiResponse;
import com.zencode.backend.common.exception.BadRequestException;
import com.zencode.backend.domain.catalog.DataCategory;
import com.zencode.backend.service.ApiCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class ApiCatalogController {

    private final ApiCatalogService catalogService;

    @GetMapping
    public ApiResponse<List<ApiDataSourceDto>> findAll() {
        return ApiResponse.ok(catalogService.findAll());
    }

    @GetMapping("/category/{category}")
    public ApiResponse<List<ApiDataSourceDto>> findByCategory(@PathVariable("category") String category) {
        DataCategory dataCategory = resolveCategory(category);
        return ApiResponse.ok(catalogService.findByCategory(dataCategory));
    }

    @GetMapping("/{slug}")
    public ApiResponse<ApiDataSourceDto> findBySlug(@PathVariable("slug") String slug) {
        return ApiResponse.ok(catalogService.findBySlug(slug));
    }

    private DataCategory resolveCategory(String category) {
        try {
            return DataCategory.valueOf(category.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("不支持的数据源分类: " + category);
        }
    }
}

