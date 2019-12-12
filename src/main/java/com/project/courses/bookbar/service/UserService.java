package com.project.courses.bookbar.service;

import com.project.courses.bookbar.entity.Book;
import com.project.courses.bookbar.entity.User;
import com.project.courses.bookbar.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public Set<Book> findBooksToBuy(Long id) {
        return userRepo.findById(id).orElseThrow(EntityNotFoundException::new).getWantToBuyBooks();
    }

    public Set<Book> findBooksToRead(Long id) {
        return userRepo.findById(id).orElseThrow(EntityNotFoundException::new).getWantToReadBooks();
    }

    public Set<Book> findReadBooks(Long id) {
        return userRepo.findById(id).orElseThrow(EntityNotFoundException::new).getReadBooks();
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}