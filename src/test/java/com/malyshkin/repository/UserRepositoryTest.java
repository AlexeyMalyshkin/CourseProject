package com.malyshkin.repository;


import com.malyshkin.config.DataSourceConfig;
import com.malyshkin.config.SpringDataConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataSourceConfig.class, SpringDataConfig.class})
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void test() throws Exception {

    }
}