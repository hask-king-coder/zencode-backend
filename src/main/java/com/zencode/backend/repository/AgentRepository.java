package com.zencode.backend.repository;

import com.zencode.backend.domain.agent.AgentEntity;
import com.zencode.backend.domain.agent.AgentRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgentRepository extends JpaRepository<AgentEntity, UUID> {

    List<AgentEntity> findByRole(AgentRole role);

    Optional<AgentEntity> findByBlueprintId(String blueprintId);
}

