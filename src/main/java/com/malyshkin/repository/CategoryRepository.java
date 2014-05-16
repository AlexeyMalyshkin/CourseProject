package com.malyshkin.repository;

import com.malyshkin.entity.Category;
import com.malyshkin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "from Category where user=:user and date between :monthAgoDate and :currentDate")
    public List<Category> findForCurrentMonth(@Param("user") User user,
                                              @Param("monthAgoDate") Date monthAgo,
                                              @Param("currentDate") Date currentDate);

}
