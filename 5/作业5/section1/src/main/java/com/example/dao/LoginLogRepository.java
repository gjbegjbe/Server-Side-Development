package com.example.dao;

import com.example.domain.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Integer> {

    @Modifying
    @Query(value =
            "INSERT INTO t_login_log(user_id,ip,login_datetime) " +
                    "VALUES(:#{#loginLog.userId},:#{#loginLog.ip},:#{#loginLog.loginDate})", nativeQuery = true)
    void insertLoginLog(@Param("loginLog") LoginLog loginLog);
}