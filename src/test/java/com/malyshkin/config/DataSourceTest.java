package com.malyshkin.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( loader=AnnotationConfigContextLoader.class, classes={DataSourceConfig.class})
public class DataSourceTest {
    @Autowired
    DataSource dataSource;

    @Test
    public void dataSourceConnected() throws SQLException {
        System.out.println("DataSource Connected: " + dataSource.getConnection().isClosed());

        assert !dataSource.getConnection().isClosed();
    }

}