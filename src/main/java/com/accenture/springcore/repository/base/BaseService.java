package com.accenture.springcore.repository.base;

import com.accenture.springcore.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityExistsException;
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
        return repository.findById(id).orElseThrow(EntityExistsException::new);
    }
}
