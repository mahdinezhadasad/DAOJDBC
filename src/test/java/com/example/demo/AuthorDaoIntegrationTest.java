package com.example.demo;

import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.AuthorDaoImpl;
import com.example.demo.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
//@ComponentScan(basePackages ="com.example.demo.dao")
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {
    
    @Autowired
    AuthorDao  authorDao;
    
    @Test
    void testGetAuthor() throws SQLException {
        
        Author author = authorDao.getById (1L);
        assertThat(author).isNotNull();
        
    }
}