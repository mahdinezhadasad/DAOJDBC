package com.example.demo.daoJdbcTemplate;

import com.example.demo.domain.Author;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorDao {
    
    Author getById(Long id);
    
    List<Author> findAllAuthorWithPageable(Pageable pageable);
    
    List<Author> findAllBooksSortByLastName(Pageable pageable);
    
    Author findAuthorByName(String firstName , String lastName);
    Author saveNewAuthor(Author author);
    Author update(Author author);
    void deleteAuthorById(Long id);
    
    Author updateAuthor(Author saved);
    
    List<Author> findAuthorByLastName(String lastName, Pageable pageable);
}