package com.malyshkin.repository;

import com.malyshkin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(@Param("login") String login);

    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

}
