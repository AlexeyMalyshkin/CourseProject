package com.malyshkin.dao;

import com.malyshkin.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoImpl implements UserDao {
    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Transactional
//    public void create(User user) {
//        sessionFactory.getCurrentSession().save(user);
//        sessionFactory.getCurrentSession().flush();
//
//    }
//
//    @Transactional
//    public void update(User user) {
//        sessionFactory.getCurrentSession().update(user);
//        sessionFactory.getCurrentSession().flush();
//
//    }
//
//    @Transactional
//    public void remove(User user) {
//        sessionFactory.getCurrentSession().delete(user);
//        sessionFactory.getCurrentSession().flush();
//
//    }
//
//    @SuppressWarnings("unchecked")
//    @Transactional
//    public List<User> findAll() {
//        return sessionFactory.getCurrentSession().createQuery("from User")
//                .list();
//    }
//
//    @Transactional
//    public User findByLogin(String login) {
//        Query query = sessionFactory.getCurrentSession().createQuery(
//                "from User where login = :login");
//        query.setParameter("login", login);
//        User user = (User) query.uniqueResult();
//        return user;
//    }
//
//    @Transactional
//    public User findByEmail(String email) {
//        Query query = sessionFactory.getCurrentSession().createQuery(
//                "from User where email = :email");
//        query.setParameter("email", email);
//        User user = (User) query.uniqueResult();
//        return user;
//    }

}
