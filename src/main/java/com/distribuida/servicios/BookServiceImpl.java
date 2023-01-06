package com.distribuida.servicios;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import com.distribuida.rest.BookMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Handle;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService{
    @Inject
    private BookService bookService;

    private List<Book> books = new ArrayList<>();

    @Inject
    private DbConfig dbConfig;

    public Book findById(int id){
        Handle handle = dbConfig.jdbi().open();
        Book book = handle.select("SELECT * FROM books WHERE id =?",id).map(new BookMapper()).one();
        return book;
    }

    public List<Book> findAll(){
        Handle handle = dbConfig.jdbi().open();
        books = handle.createQuery("SELECT * FROM books").mapToBean(Book.class).list();
        return books;
    }

    @Override
    public void insert(Book book) {
        Handle handle = dbConfig.jdbi().open();
        handle.createUpdate("INSERT INTO books (isbn,title,author,price) VALUES (:isbn,:title,:author,:price)").bind("isbn",book.getIsbn())
                .bind("title",book.getTitle())
                .bind("author",book.getAuthor())
                .bind("price",book.getPrice()).execute();
    }

    @Override
    public void update(Book book) {
        Handle handle = dbConfig.jdbi().open();
        handle.createUpdate("UPDATE books SET isbn= :isbn" + ",title= :title"+ ",author= :author"+ ",price= :price" + " WHERE id= :id").bind("isbn",book.getIsbn())
                .bind("title",book.getTitle())
                .bind("author",book.getAuthor())
                .bind("price",book.getPrice())
                .bind("id",book.getId()).execute();
    }

    @Override
    public void delete(int id) {
        Handle handle = dbConfig.jdbi().open();
        handle.createUpdate("DELETE FROM books WHERE id= :id").bind("id",id).execute();
    }




}
