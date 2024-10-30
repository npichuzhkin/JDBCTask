package com.npichuzhkin.JDBCTask;

import com.npichuzhkin.JDBCTask.controllers.BooksController;
import com.npichuzhkin.JDBCTask.models.Book;
import com.npichuzhkin.JDBCTask.services.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BooksController.class)
public class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void showAllBooksShouldReturnBookList() throws Exception {
        Book book = new Book(UUID.randomUUID(), "Book Title", "Author Name", 2023);
        when(bookService.findAll()).thenReturn(Collections.singletonList(book));

        mockMvc.perform(get("/api/v1/books/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book Title"))
                .andExpect(jsonPath("$[0].author").value("Author Name"));
    }

    @Test
    public void showOneBookShouldReturnBookById() throws Exception {
        UUID bookId = UUID.randomUUID();
        Book book = new Book(bookId, "Book Title", "Author Name", 2023);
        when(bookService.findById(bookId)).thenReturn(book);

        mockMvc.perform(get("/api/v1/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book Title"))
                .andExpect(jsonPath("$.author").value("Author Name"));
    }

    @Test
    public void addNewBookShouldReturnOkStatus() throws Exception {
        String newBookJson = "{\"title\":\"New Book\", \"author\":\"New Author\", \"publicationYear\":2023}";

        mockMvc.perform(post("/api/v1/books/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newBookJson))
                .andExpect(status().isOk());

        Mockito.verify(bookService).save(Mockito.any(Book.class));
    }

    @Test
    public void updateBookShouldReturnOkStatus() throws Exception {
        UUID bookId = UUID.randomUUID();
        String updatedBookJson = "{\"title\":\"Updated Book\", \"author\":\"Updated Author\", \"publicationYear\":2024}";

        mockMvc.perform(put("/api/v1/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedBookJson))
                .andExpect(status().isOk());

        Mockito.verify(bookService).update(eq(bookId), Mockito.any(Book.class));
    }

    @Test
    public void deleteBookShouldReturnOkStatus() throws Exception {
        UUID bookId = UUID.randomUUID();

        mockMvc.perform(delete("/api/v1/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(bookService).delete(eq(bookId));
    }
}
