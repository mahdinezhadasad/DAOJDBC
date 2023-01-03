package com.example.demo.daoJdbcTemplate;

import com.example.demo.domain.Author;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AuthorDaoImpl implements AuthorDao {
    
    private final JdbcTemplate jdbcTemplate;
    
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Author getById(Long id) {
        String sql = "select author.id as id, first_name , last_name, book.id as book_id, book.isbn , bookdb2.book.publisher, bookdb2.book.publisher,  bookdb2.book.title from author\n" +
                "left outer join book on author.id = book.author_id where author.id= ?";
        
        //return jdbcTemplate.queryForObject ("SELECT * FROM author where id = ?", getRowMapper (), id);
        
        return jdbcTemplate.query (sql ,new AuthorExtractor (),id);
    }
    @Override
    public List<Author> findAllAuthorWithPageable(Pageable pageable) {
        return jdbcTemplate.query ("SELECT * FROM Author limit ? offset ? ",getRowMapper (),
                
                pageable.getPageSize (),pageable.getOffset ());
    }
    
    @Override
    public List<Author> findAllBooksSortByLastName(Pageable pageable) {
        String sql = "SELECT * FROM Author order by Last_name " + pageable.getSort ().getOrderFor ("last_name").getDirection ().name ()
                + " limit ? offset ?";
        
        System.out.println (sql);
        return jdbcTemplate.query (sql, getRowMapper (), pageable.getPageSize (), pageable.getOffset ());
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
    
    @Override
    public List<Author> findAuthorByLastName(String lastName, Pageable pageable) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM author WHERE last_name = ? ");
        if(pageable.getSort ().getOrderFor ("firstname") != null){
            
            sb.append ("order by first_name ").append (pageable.getSort ().getOrderFor ("firstname").getDirection ().name ());
        }
        sb.append (" limit ? offset ?");
        return jdbcTemplate.query (sb.toString(),getRowMapper(),lastName,pageable.getPageSize (),pageable.getOffset ());
    }
    
    private RowMapper<Author> getRowMapper(){
        
        return new AuthorMapper();
    }
}