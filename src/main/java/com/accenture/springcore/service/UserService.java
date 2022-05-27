package com.accenture.springcore.service;

import com.accenture.springcore.exception.customExceptions.EntityNotFoundException;
import com.accenture.springcore.model.User;
import com.accenture.springcore.repository.UserRepository;
import com.accenture.springcore.utils.validator.ValidUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService extends BaseService<User, Integer> {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(@ValidUser User user) {
        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer integer) {
        return userRepository.findById(integer)
                .orElseThrow(() -> new EntityNotFoundException("No user with this ID in database."));
    }

    public User updateUser(Integer id, @ValidUser User sourceUser) {
        User target = findById(id);
        target.setUsername(sourceUser.getUsername());
        target.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(sourceUser.getPassword()));
        return userRepository.save(target);
    }

    public void deleteUser(Integer id) {
        userRepository.findById(id).ifPresentOrElse(
                user -> userRepository.deleteById(user.getId()), NoSuchElementException::new);
    }
}
