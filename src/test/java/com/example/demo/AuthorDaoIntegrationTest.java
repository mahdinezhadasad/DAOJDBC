package com.example.demo;

import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.AuthorDaoImpl;
import com.example.demo.dao.BookDao;
import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
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
@ComponentScan(basePackages ="com.example.demo.dao")
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {
    
    @Autowired
    AuthorDao  authorDao;
    
    @Autowired
    BookDao bookDao;
    
    
    @Test
    void testDeletBook(){
        Book book = new Book ();
        book.setIsbn ("1234");
        book.setPublisher ("Self");
        book.setTitle ("my book");
        Book saved = bookDao.saveNewBook (book);
        bookDao.deleteBookById (saved.getId ());
        
        Book deleted = bookDao.getById (saved.getId());
        
        assertThat (deleted).isNull ();
        
        
    }
    
    @Test
    void updateBook(){
        Book book = new Book ();
        book.setIsbn ("1234");
        book.setPublisher ("Self");
        book.setTitle ("my book");
        
        Author author = new Author();
        author.setId (3L);
        book.setAuthor (author);
        
        Book saved = bookDao.saveNewBook (book);
        bookDao.deleteBookById (saved.getId ());
        
        Book deleted = bookDao.getById (saved.getId());
        
        assertThat (deleted).isNull ();
        
        
    }
    
    @Test
    void saveBook(){
        Book book = new Book ();
        book.setIsbn ("1234");
        book.setPublisher ("Self");
        book.setTitle ("my book");
        
        Author author = new Author();
        author.setId (3L);
        book.setAuthor (author);
        
        Book saved = bookDao.saveNewBook (book);
        
        assertThat (saved).isNotNull ();
        
        
    }
    @Test
    void testGetAuthor() throws SQLException {
        
        Author author = authorDao.getById (1L);
        assertThat(author).isNotNull();
        
    }
    
    @Test
    void testGetAuthorByName(){
    
         Author author = new Author ();
         
         author.setFirstName ("John");
         author.setLastName ("Thompson");
         Author saved = authorDao.saveNewAuthor (author);
         
         assertThat (saved).isNotNull ();
    }
    
    @Test
    void testUpdateAuthor(){
        Author author = new Author();
        author.setFirstName ("john");
        author.setLastName ("tiago");
        
        Author saved = authorDao.saveNewAuthor (author);
        saved.setLastName ("Thompson");
        Author updated = authorDao.updatedAuthor(saved);
        
        assertThat (updated.getLastName ()).isEqualTo ("Thompson");
        
    }
    @Test
    void testSaveAuthor() throws SQLException {
        
        Author author = authorDao.getById (1L);
        
        assertThat (author).isNotNull();
    }
    
    @Test
    void testDeleteAuthor() throws SQLException {
        Author author = new Author();
        author.setFirstName ("john");
        author.setLastName ("m");
        
        Author saved = authorDao.saveNewAuthor (author);
        
        authorDao.deleteAuthorById(saved.getId ());
        
        Author deleted = authorDao.getById (saved.getId());
        
        assertThat(deleted).isNull ();
        
    }
}