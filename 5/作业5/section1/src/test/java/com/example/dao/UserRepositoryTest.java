package com.example.dao;


import com.example.config.RootConfig;
import com.example.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.*;


@ContextConfiguration(classes = {RootConfig.class})
public class UserRepositoryTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void hasMatchUser() {
        int count = userRepository.countByUserNameAndPassword("admin", "123456");
        assertTrue(count > 0);
    }

    @Test
    public void findUserByUserName() {
        User user = userRepository.findUserByUserName("admin");
        assertNotNull(user);
        assertEquals(user.getUserName(), "admin");
    }

}
