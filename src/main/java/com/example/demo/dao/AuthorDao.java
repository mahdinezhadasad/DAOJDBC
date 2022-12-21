package com.example.demo.dao;

import com.example.demo.domain.Author;

import java.sql.SQLException;

public interface AuthorDao {
    
    Long getById(Long id) throws SQLException;
    Long findAuthorByName(String firstName, String lastName);
    
    Long saveNewAuthor(Author author);
    
    Long updatedAuthor(Long saved);
    
    void deleteAuthorById(Long id);
}