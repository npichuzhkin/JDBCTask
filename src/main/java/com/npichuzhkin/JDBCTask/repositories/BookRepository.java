package com.npichuzhkin.JDBCTask.repositories;

import com.npichuzhkin.JDBCTask.models.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository {
    List<Book> findAll();
    Book findById(UUID id);
    void save(Book newBook);
    void update(UUID id,Book updatedBook);
    void delete(UUID id);
}
