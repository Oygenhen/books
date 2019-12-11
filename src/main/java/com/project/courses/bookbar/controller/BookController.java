package com.project.courses.bookbar.controller;

import com.project.courses.bookbar.entity.Book;
import com.project.courses.bookbar.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") Long id) {
        Book book = bookService.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable("id") Long id,
                                       @RequestBody Book book) {
        book.setId(id);
        Book updatedBook = bookService.update(book);
        return ResponseEntity.ok(updatedBook);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book createdBook = bookService.create(book);
        return ResponseEntity.ok(createdBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable("id") Long id) {
        Book deletedBook = bookService.findById(id).orElseThrow(EntityNotFoundException::new);
        bookService.delete(deletedBook);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{bookId}/user/{userId}")
    public ResponseEntity<Book> addBookToList(@PathVariable("bookId") Long bookId,
                                              @PathVariable("userId") Long userId,
                                              @RequestParam(name = "type") String listType) {
        Book book = bookService.addBookToList(bookId, userId, listType);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{bookId}/user/{userId}")
    public ResponseEntity<Book> deleteBookFromList(@PathVariable("bookId") Long bookId,
                                                   @PathVariable("userId") Long userId,
                                                   @RequestParam(name = "type") String listType) {
        Book book = bookService.deleteBookFromList(bookId, userId, listType);
        return ResponseEntity.ok(book);
    }
}






