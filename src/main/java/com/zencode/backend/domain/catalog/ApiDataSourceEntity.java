package com.zencode.backend.domain.catalog;

import com.zencode.backend.domain.agent.AgentRole;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "api_data_sources")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDataSourceEntity {

    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 48)
    private DataCategory category;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "free_tier", nullable = false)
    private boolean freeTier;

    @Column(name = "description", length = 2048)
    private String description;

    @Column(name = "notes", length = 1024)
    private String notes;

    @Column(name = "rate_limit", length = 128)
    private String rateLimit;

    @Column(name = "best_for", length = 256)
    private String bestFor;

    @ElementCollection
    @CollectionTable(name = "api_data_source_tags", joinColumns = @JoinColumn(name = "data_source_id"))
    @Column(name = "tag", length = 64)
    @Builder.Default
    private Set<String> tags = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "primary_role", length = 32)
    private AgentRole primaryRole;

    @ElementCollection(targetClass = AgentRole.class)
    @CollectionTable(name = "api_data_source_support_roles", joinColumns = @JoinColumn(name = "data_source_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 32)
    @Builder.Default
    private Set<AgentRole> alsoSupports = new HashSet<>();
}

