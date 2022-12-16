package com.example.demo.dao;

import com.example.demo.domain.Author;

import java.sql.SQLException;

public interface AuthorDao {
    
    Author getById(Long id) throws SQLException;
}