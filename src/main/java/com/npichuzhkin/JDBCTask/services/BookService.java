package com.npichuzhkin.JDBCTask.services;

import com.npichuzhkin.JDBCTask.models.Book;
import com.npichuzhkin.JDBCTask.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class BookService implements BookRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book findById(UUID id) {
        return jdbcTemplate.query("select * from book where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void save(Book newBook) {
        jdbcTemplate.update("insert into book (title, author, publication_year) values (?, ?, ?)", newBook.getTitle(), newBook.getAuthor(), newBook.getPublicationYear());
    }

    @Override
    public void update(UUID id, Book updatedBook) {
        jdbcTemplate.update("update book set title=?, author=?, publication_year=? where id=?", updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getPublicationYear(), id);
    }

    @Override
    public void delete(UUID id) {
        jdbcTemplate.update("delete from book where id=?", id);
    }
}
