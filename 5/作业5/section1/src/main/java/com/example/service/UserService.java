package com.example.service;

import com.example.dao.LoginLogRepository;
import com.example.dao.UserRepository;
import com.example.domain.LoginLog;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private LoginLogRepository loginLogRepository;

    @Autowired
    public UserService(UserRepository userRepository,LoginLogRepository loginLogRepository){
        this.loginLogRepository = loginLogRepository;
        this.userRepository = userRepository;
    }

    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userRepository.countByUserNameAndPassword(userName, password);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    public boolean saveLog(User user) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        loginLogRepository.insertLoginLog(loginLog);
        return true;
    }
}
