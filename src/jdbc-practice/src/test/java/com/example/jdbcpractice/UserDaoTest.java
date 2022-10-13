package com.example.jdbcpractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {
    @BeforeEach
    @DisplayName("테스트를 실행 하기 전 테이블을 생성하고 DataSource 커넥션을 획득")
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource()); // dataSource 를 받아와 execute 한다
    }

    @Test
    @DisplayName("DAO 로 생성한 객체가 아래 new User 객체와 같은지 확인")
    void createTest() throws SQLException {
        UserDao userDao = new UserDao();
        userDao.create(new User("wizard", "password", "name", "email"));

        User user = userDao.findByUserId("wizard");
        assertThat(user).isEqualTo(new User("wizard", "password", "name", "email"));
    }
}
