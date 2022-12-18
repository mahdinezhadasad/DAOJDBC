package com.example.demo.dao;

import com.example.demo.domain.Author;

import java.sql.SQLException;

public interface AuthorDao {
    
    Author getById(Long id) throws SQLException;
    Author  findAuthorByName(String firstName,String lastName);
    
    Author saveNewAuthor(Author author);
    
    Author updatedAuthor(Author saved);
    
    void deleteAuthorById(Long id);
}