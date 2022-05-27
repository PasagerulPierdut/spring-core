package com.accenture.springcore.service;

import com.accenture.springcore.exception.customExceptions.EntityNotFoundException;
import com.accenture.springcore.model.BaseEntity;
import com.accenture.springcore.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseService<T extends BaseEntity, ID> {

    private BaseRepository<T, ID> repository;

    @Autowired
    public void setRepository(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(ID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("The specified ID is not in database."));
    }
}
