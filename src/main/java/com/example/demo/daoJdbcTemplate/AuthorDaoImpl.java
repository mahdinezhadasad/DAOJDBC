package com.example.demo.daoJdbcTemplate;

import com.example.demo.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class AuthorDaoImpl implements AuthorDao {
    
    private final JdbcTemplate jdbcTemplate;
    
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Author getById(Long id) {
        return jdbcTemplate.queryForObject ("SELECT * FROM author where id = ?", getRowMapper (), id);
    }
    
    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        
        
        return jdbcTemplate.queryForObject ("SELECT * FROM author where first_name = ? and last_name = ?", getRowMapper (), firstName, lastName);
    }
    
    @Override
    public Author saveNewAuthor(Author author) {
        
        jdbcTemplate.update ("INSERT INTO author (first_name, last_name) VALUES (?, ?)",
                author.getFirstName (), author.getLastName ());
        
        Long createdId = jdbcTemplate.queryForObject ("SELECT id FROM author ORDER BY id DESC LIMIT 1", Long.class);
        
        
        return this.getById (createdId);
    }
    
    @Override
    public Author update(Author author) {
        return null;
    }
    
    
    @Override
    public void deleteAuthorById(Long id) {
        
        jdbcTemplate.update ("DELETE  FROM author WHERE id = ?",id);
        
        
    
    }
    
    @Override
    public Author updateAuthor(Author author) {
        jdbcTemplate.update ("UPDATE author SET first_name = ?,last_name = ? WHERE id  = ?",
                author.getFirstName(), author.getLastName (),author.getId ());
        return this.getById(author.getId());
    }
    
    private RowMapper<Author> getRowMapper(){
        
        return new AuthorMapper();
    }
}