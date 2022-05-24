package com.accenture.springcore.repository;

import com.accenture.springcore.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {
}
