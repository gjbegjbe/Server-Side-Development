package com.example.dao;

import com.example.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    int countByUserNameAndPassword(String userName, String password);

    @Cacheable("userCache")
    User findUserByUserName(String userName);

    @Modifying
    @Query(value = "UPDATE t_user SET last_visit=:#{#user.lastVisit},last_ip=:#{#user.lastIp} " +
            "WHERE user_id =:#{#user.userId}", nativeQuery = true)
    void updateLoginInfo(@Param("user") User user);
}
