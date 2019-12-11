package com.project.courses.bookbar.controller;

import com.project.courses.bookbar.entity.Author;
import com.project.courses.bookbar.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        List<Author> authors = authorService.findAll();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable("id") Long id) {
        Author author = authorService.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable("id") Long id,
                                         @RequestBody Author author) {
        author.setId(id);
        Author updatedAuthor = authorService.update(author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author) {
        Author createdAuthor = authorService.create(author);
        return ResponseEntity.ok(createdAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable("id") Long id) {
        Author deletedAuthor = authorService.findById(id).orElseThrow(EntityNotFoundException::new);
        authorService.delete(deletedAuthor);
        return ResponseEntity.notFound().build();
    }
}