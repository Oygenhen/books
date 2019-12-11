package com.project.courses.bookbar.controller;

import com.project.courses.bookbar.entity.Book;
import com.project.courses.bookbar.entity.User;
import com.project.courses.bookbar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> books = userService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        User book = userService.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{id}/books/read")
    public ResponseEntity<Set<Book>> findAllReadBooks(@PathVariable("id") Long id) {
        Set<Book> books = userService.findReadBooks(id);
        return ResponseEntity.ok(books);
    }

   @GetMapping("/{userId}/books/to-buy")
    public ResponseEntity<Set<Book>> findAllBooksToBuy(@PathVariable("userId") Long id) {
        Set<Book> books = userService.findBooksToBuy(id);
       return ResponseEntity.ok(books);
    }

   @GetMapping("/{userId}/books/to-read")
    public ResponseEntity<Set<Book>> findAllBooksToRead(@PathVariable("userId") Long id) {
       Set<Book> books = userService.findBooksToBuy(id);
      return ResponseEntity.ok(books);
    }
}
