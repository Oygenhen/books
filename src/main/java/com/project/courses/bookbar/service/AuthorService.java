package com.project.courses.bookbar.service;

import com.project.courses.bookbar.entity.Author;
import com.project.courses.bookbar.repo.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public List<Author> findAll() {
        return authorRepo.findAll();
    }

    public Optional<Author> findById(Long id) {
        return authorRepo.findById(id);
    }

    public Author create(Author author) {
        return authorRepo.save(author);
    }

    public Author update(Author author) {
        return authorRepo.save(author);
    }

    public void delete(Author author) {
        authorRepo.delete(author);
    }
}

