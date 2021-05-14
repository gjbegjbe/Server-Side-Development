package com.example.service;

import com.example.domain.User;
import org.junit.Test;
import com.example.dao.LoginLogRepository;
import com.example.dao.UserRepository;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Test
    public void hasMatchUser() {
        UserRepository userRepository = mock(UserRepository.class);
        LoginLogRepository loginLogRepository = mock(LoginLogRepository.class);
        when(userRepository.countByUserNameAndPassword("admin", "123456")).thenReturn(1);
        when(userRepository.countByUserNameAndPassword("admin", "1111")).thenReturn(0);

        UserService userService = new UserService(userRepository, loginLogRepository);
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertFalse(b2);
    }


    @Test
    public void findUserByUserName() {
        UserRepository userRepository = mock(UserRepository.class);
        LoginLogRepository loginLogRepository = mock(LoginLogRepository.class);
        when(userRepository.findUserByUserName("admin")).thenReturn(new User(1, "admin"));

        UserService userService = new UserService(userRepository, loginLogRepository);
        User user = userService.findUserByUserName("admin");
        assertEquals(user.getUserName(), "admin");
    }

    @Test
    public void loginSuccess() {
        UserRepository userRepository = mock(UserRepository.class);
        LoginLogRepository loginLogRepository = mock(LoginLogRepository.class);
        when(userRepository.findUserByUserName("admin")).thenReturn(new User(1, "admin"));

        UserService userService = new UserService(userRepository, loginLogRepository);
        User user = userService.findUserByUserName("admin");
        user.setUserId(1);
        user.setUserName("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());
        userService.saveLog(user);
    }
}

