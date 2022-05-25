package com.accenture.springcore.repository;

import com.accenture.springcore.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Integer>{
}
