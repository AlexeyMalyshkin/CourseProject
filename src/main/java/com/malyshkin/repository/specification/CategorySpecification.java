package com.malyshkin.repository.specification;

import com.malyshkin.entity.Category;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;

public class CategorySpecification implements Specification<Category> {

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        int i =0;
        return cb.greaterThan(root.get(Category_.date).as(Date.class), cb.currentDate());
    }
}
