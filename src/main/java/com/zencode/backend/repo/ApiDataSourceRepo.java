package com.zencode.backend.repo;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zencode.backend.api.dto.ApiDataSourceDto;
import com.zencode.backend.mapper.ApiDataSourceMapper;
import com.zencode.backend.domain.catalog.ApiDataSourceEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiDataSourceRepo extends ServiceImpl<ApiDataSourceMapper, ApiDataSourceEntity> {

    public ApiDataSourceDto toDto(ApiDataSourceEntity entity) {
        return new ApiDataSourceDto(
                entity.getId(),
                entity.getSlug(),
                entity.getName(),
                entity.getCategory(),
                entity.getProvider(),
                entity.getUrl(),
                entity.getFreeTier(),
                entity.getDescription(),
                entity.getNotes(),
                entity.getRateLimit(),
                entity.getBestFor(),
                entity.getPrimaryRole(),
                entity.getTags(),
                entity.getAlsoSupports()
        );
    }
}

