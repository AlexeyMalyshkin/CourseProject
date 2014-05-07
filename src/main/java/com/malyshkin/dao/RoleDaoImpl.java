package com.malyshkin.dao;

import com.malyshkin.domain.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleDaoImpl implements RoleDao {
    @Override
    public void create(Role role) {

    }

    @Override
    public void update(Role role) {

    }

    @Override
    public void remove(Role role) {

    }

    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public String findNameById(long id) {
        return null;
    }

//    @Autowired
//    private SessionFactory sessionFactory;

//    public RoleDaoImpl(){
//
//    }
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

//    @Transactional
//    public void create(Role role) {
//        sessionFactory.getCurrentSession().save(role);
//        sessionFactory.getCurrentSession().flush();
//
//    }
//
//    @Transactional
//    public void update(Role role) {
//        sessionFactory.getCurrentSession().saveOrUpdate(role);
//        sessionFactory.getCurrentSession().flush();
//
//    }
//
//    @Transactional
//    public void remove(Role role) {
//        sessionFactory.getCurrentSession().delete(role);
//        sessionFactory.getCurrentSession().flush();
//
//    }
//
//    @Transactional
//    public Role findByName(String name) {
//        Query query = sessionFactory.getCurrentSession().createQuery(
//                "from Role where name = :name ");
//        query.setParameter("name", name);
//        return (Role) query.uniqueResult();
//
//    }
//
//    @Transactional
//    public String findNameById(long id) {
//        return ((Role) sessionFactory.getCurrentSession().get(Role.class, id))
//                .getName();
//    }

}
