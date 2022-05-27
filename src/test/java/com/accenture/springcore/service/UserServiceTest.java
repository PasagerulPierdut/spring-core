package com.accenture.springcore.service;

import com.accenture.springcore.model.User;
import com.accenture.springcore.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository mockUserRepository;

    @InjectMocks
    UserService userService;

    @Test
    void createUser() {
    }

    @Test
    void verifyIfFindAllCallsTheRepository() {
        Mockito.when(mockUserRepository.findAll()).thenReturn(new ArrayList<>());

        userService.findAll();

        Mockito.verify(mockUserRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void verifyIfUserUpdatesSuccessfully() {
        User testUser = new User("userN", "pass123", new HashSet<>());
        Mockito.when(mockUserRepository.findById(1)).thenReturn(Optional.of(testUser));

        userService.updateUser(1, new User("lol", "123", new HashSet<>()));

        Assertions.assertEquals("lol", testUser.getUsername());
    }
}