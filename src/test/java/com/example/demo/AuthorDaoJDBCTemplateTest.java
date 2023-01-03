package com.example.demo;


import com.example.demo.daoJdbcTemplate.AuthorDao;
import com.example.demo.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.example.demo.daoJdbcTemplate"})
public class AuthorDaoJDBCTemplateTest {
    
    
    @Autowired
    AuthorDao  authorDao;
    
    
    @Test
    void findAllAuthorByLastName(){
        List<Author> authors = authorDao.findAuthorByLastName("Smith", PageRequest.of (0,10));
        
        assertThat(authors).isNotNull();
        assertThat(authors.size ()).isEqualTo (5);
        
        
    }
    
    @Test
    void findAllAuthorsByLastNameSortLastNameDesc(){
        
        List<Author> authors = authorDao.findAuthorByLastName ("Smith" , PageRequest.of(0,10, Sort.by (Sort.Order.desc ("firstname"))));
        
        assertThat (authors).isNotNull ();
        assertThat(authors.size ()).isEqualTo (5);
        assertThat(authors.get (0).getFirstName ()).isEqualTo ("Yeum");
        
    }
    
    @Test
    void findAllAuthorsByLastNameSortLastNameAsc(){
        
        List<Author>  authors = authorDao.findAuthorByLastName ("Smith" ,PageRequest.of(0,10 ,Sort.by(Sort.Order.asc ("firstname"))));
        
        assertThat (authors).isNotNull();
        assertThat (authors.size ()).isEqualTo (5);
        assertThat (authors.get (0).getFirstName ()).isEqualTo ("Ahmed");
        
    }
    
    @Test
    void findAllAuthorsByLastNameAllRecs(){
        List<Author>  authors = authorDao.findAuthorByLastName ("Smith",PageRequest.of(0,100));
        assertThat (authors).isNotNull();
        assertThat (authors.size ()).isEqualTo (20);
        
    }
}