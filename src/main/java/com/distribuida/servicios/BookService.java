package com.distribuida.servicios;

import com.distribuida.db.Book;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(Book.class)
public interface BookService {
    @SqlQuery("Select * from books where id=:id")
    Book findById(int id);
    @SqlQuery("Select * from books")
    List<Book> findAll();
    void insert (Book book);
    void update (Book book);
    void delete (int id);
}
