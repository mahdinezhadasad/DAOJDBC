package com.example.demo.daoJdbcTemplate;

import com.example.demo.domain.Author;

public interface AuthorDao {
    
    Author getById(Long id);
    
    Author findAuthorByName(String firstName , String lastName);
    Author saveNewAuthor(Author author);
    Author update(Author author);
    void deleteAuthorById(Long id);
    
    Author updateAuthor(Author saved);
}