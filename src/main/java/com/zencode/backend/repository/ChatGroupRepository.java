package com.zencode.backend.repository;

import com.zencode.backend.domain.group.ChatGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatGroupRepository extends JpaRepository<ChatGroupEntity, UUID> {
}

