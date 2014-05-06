package com.malyshkin.service;

import com.malyshkin.dao.UserDao;
import com.malyshkin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceOld {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void create(User user) {
        userDao.create(user);

    }

    public void update(User user) {
        userDao.update(user);

    }

    public void remove(User user) {

        userDao.remove(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

}
