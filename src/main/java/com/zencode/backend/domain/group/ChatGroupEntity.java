package com.zencode.backend.domain.group;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "chat_groups")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatGroupEntity {

    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "note", length = 512)
    private String note;

    @Column(name = "twitter_handle", length = 64)
    private String twitterHandle;

    @Column(name = "telegram_handle", length = 64)
    private String telegramHandle;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ElementCollection
    @CollectionTable(name = "chat_group_members", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "member_role", length = 32)
    @Builder.Default
    private Set<String> members = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "chat_group_signal_agents", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "agent_id", columnDefinition = "uuid")
    @Builder.Default
    private Set<UUID> signalAgentIds = new HashSet<>();

    @Column(name = "analyst_agent_id", columnDefinition = "uuid")
    private UUID analystAgentId;

    @Column(name = "trader_agent_id", columnDefinition = "uuid")
    private UUID traderAgentId;

    @Column(name = "review_agent_id", columnDefinition = "uuid")
    private UUID reviewAgentId;
}

