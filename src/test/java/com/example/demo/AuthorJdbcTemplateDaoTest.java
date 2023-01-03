package com.example.demo;

import com.example.demo.daoJdbcTemplate.AuthorDao;
import com.example.demo.daoJdbcTemplate.AuthorDaoImpl;
import com.example.demo.daoJdbcTemplate.BookDaoImpl;
import com.example.demo.daoJdbcTemplate.Bookdao;
import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages ="com.example.demo.daoJdbcTemplate")
@Import({AuthorDaoImpl.class, BookDaoImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorJdbcTemplateDaoTest {
    
    
    @Autowired
    AuthorDao authorDao;
    
    @Autowired
    Bookdao bookDao;
    
    
    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        Book saved = bookDao.saveNewBook(book);
        
        bookDao.deleteBookById(saved.getId());
        
        assertThrows(EmptyResultDataAccessException.class, () -> {
            bookDao.getById(saved.getId());
        });
    }
    
    @Test
    void updateBookTest() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthor (1L);
        Book saved = bookDao.saveNewBook(book);
        
        saved.setTitle("New Book");
        bookDao.updateBook(saved);
        
        Book fetched = bookDao.getById(saved.getId());
        
        assertThat(fetched.getTitle()).isEqualTo("New Book");
    }
    
    @Test
    void testFindAllBooks(){
      List<Book> books = bookDao.findAllBooks ();
      
      assertThat (books).isNotNull ();
      assertThat (books.size ()).isGreaterThan (4);
    
    }
    
    @Test
    void findAllBooksPage1(){
    
        
        List<Book>  books = bookDao.findAllBooks (10,0);
        
        assertThat (books).isNotNull();
        assertThat (books.size()).isEqualTo (5);
    
    }
    
    
    @Test
    void findAllBooksPage2(){
        
        
        List<Book>  books = bookDao.findAllBooks (10,100);
        
        assertThat (books).isNotNull();
        assertThat (books.size()).isEqualTo (0);
        
    }
    
    @Test
    void testFindAllBooks_pageable(){
        List<Book> books = bookDao.findAllBooks (PageRequest.of(0,10));
        
        assertThat (books).isNotNull ();
        assertThat (books.size ()).isGreaterThan (4);
        
    }
    
    @Test
    void findAllBooksPage1_pageable(){
        
        
        List<Book>  books = bookDao.findAllBooks (PageRequest.of(1,10));
        
        assertThat (books).isNotNull();
        assertThat (books.size()).isEqualTo (5);
        
    }
    
    
    @Test
    void findAllBooksPage2_pageable(){
        
        
        List<Book>  books = bookDao.findAllBooks (PageRequest.of(10,10));
        
        assertThat (books).isNotNull();
        assertThat (books.size()).isEqualTo (0);
        
    }
    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthor (1L);
        
        Book saved = bookDao.saveNewBook(book);
        
        assertThat(saved).isNotNull();
    }
    
    
    @Test
    void testGetBookByName() {
        Book book = bookDao.findBookByTitle("Clean Code");
        
        assertThat(book).isNotNull();
    }
    
    @Test
    void testGetBook() {
        Book book = bookDao.getById(3L);
        
        assertThat(book.getId()).isNotNull();
    }
    
    
    
    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");
        
        Author saved = authorDao.saveNewAuthor(author);
        
        authorDao.deleteAuthorById(saved.getId());
        
       assertThrows(TransientDataAccessException.class, () ->
       {
    
           authorDao.getById (saved.getId ());
       });
    }
    
    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");
        
        Author saved = authorDao.saveNewAuthor(author);
        
        saved.setLastName("Thompson");
        Author updated = authorDao.updateAuthor(saved);
        
        assertThat(updated.getLastName()).isEqualTo("Thompson");
    }
    
    @Test
    void testInsertAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t222");
                Author saved = authorDao.saveNewAuthor(author);
        
        assertThat(saved).isNotNull();
    }
    
    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");
        
        assertThat(author).isNotNull();
    }
    
    @Test
    void testGetAuthor() {
        
        Author author = authorDao.getById(1l);
        
        assertThat(author.getId()).isNotNull();
    }
    
    @Test
    void findAllBooksPage1_SortByTitle(){
    
        
        List<Book>  books = bookDao.findAllBooksSortByTitle (PageRequest.of (0,10, Sort.by (Sort.Order.desc ("title"))));
        
        assertThat (books).isNotNull();
        assertThat (books.size ()).isEqualTo (5);
    
    }
    
}