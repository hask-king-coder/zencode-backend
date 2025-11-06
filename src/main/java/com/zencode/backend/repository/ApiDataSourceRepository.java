package com.zencode.backend.repository;

import com.zencode.backend.domain.catalog.ApiDataSourceEntity;
import com.zencode.backend.domain.catalog.DataCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApiDataSourceRepository extends JpaRepository<ApiDataSourceEntity, UUID> {

    List<ApiDataSourceEntity> findByCategory(DataCategory category);

    Optional<ApiDataSourceEntity> findBySlug(String slug);
}

