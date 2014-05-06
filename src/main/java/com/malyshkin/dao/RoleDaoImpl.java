package com.malyshkin.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malyshkin.domain.Role;

@Service
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

//    public RoleDaoImpl(){
//
//    }
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Transactional
    public void create(Role role) {
        sessionFactory.getCurrentSession().save(role);
        sessionFactory.getCurrentSession().flush();

    }

    @Transactional
    public void update(Role role) {
        sessionFactory.getCurrentSession().saveOrUpdate(role);
        sessionFactory.getCurrentSession().flush();

    }

    @Transactional
    public void remove(Role role) {
        sessionFactory.getCurrentSession().delete(role);
        sessionFactory.getCurrentSession().flush();

    }

    @Transactional
    public Role findByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "from Role where name = :name ");
        query.setParameter("name", name);
        return (Role) query.uniqueResult();

    }

    @Transactional
    public String findNameById(long id) {
        return ((Role) sessionFactory.getCurrentSession().get(Role.class, id))
                .getName();
    }

}
