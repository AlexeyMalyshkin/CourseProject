package com.malyshkin.repository.specification;

import com.malyshkin.entity.Category;
import com.malyshkin.entity.CategoryType;
import com.malyshkin.entity.Transaction;
import com.malyshkin.entity.User;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import java.sql.Date;

public abstract class Category_ {
    public static volatile SingularAttribute<Category, Long> id;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile SingularAttribute<Category, CategoryType> type;
    public static volatile SingularAttribute<Category, Date> date;
    public static volatile SingularAttribute<Category, User> user;
    public static volatile SetAttribute<Category, Transaction> transactions;
}
