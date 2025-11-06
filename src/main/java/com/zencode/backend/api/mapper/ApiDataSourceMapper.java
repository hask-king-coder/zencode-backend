package com.zencode.backend.api.mapper;

import com.zencode.backend.api.dto.ApiDataSourceDto;
import com.zencode.backend.domain.catalog.ApiDataSourceEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiDataSourceMapper {

    public ApiDataSourceDto toDto(ApiDataSourceEntity entity) {
        if (entity == null) {
            return null;
        }

        return new ApiDataSourceDto(
                entity.getId(),
                entity.getSlug(),
                entity.getName(),
                entity.getCategory(),
                entity.getCategory().getDisplayName(),
                entity.getProvider(),
                entity.getUrl(),
                entity.isFreeTier(),
                entity.getDescription(),
                entity.getNotes(),
                entity.getRateLimit(),
                entity.getBestFor(),
                entity.getTags(),
                entity.getPrimaryRole(),
                entity.getPrimaryRole() != null ? entity.getPrimaryRole().getDisplayName() : null,
                entity.getAlsoSupports()
        );
    }
}

