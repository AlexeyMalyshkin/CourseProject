package com.malyshkin.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataSourceConfig.class})
public class DataSourceTest {
    @Autowired
    DataSource dataSource;

    @Test
    public void dataSourceConnected() throws SQLException {
        assertNotNull(dataSource.getConnection());
    }

}