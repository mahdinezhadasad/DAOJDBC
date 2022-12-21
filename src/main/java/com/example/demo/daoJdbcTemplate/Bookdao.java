package com.example.demo.daoJdbcTemplate;

import com.example.demo.domain.Book;

public interface Bookdao {
    
     
        Book getById(Long id);
        
        Book findBookByTitle(String title);
        
        Book saveNewBook(Book book);
        
        Book updateBook(Book book);
        
        void deleteBookById(Long id);
    
}