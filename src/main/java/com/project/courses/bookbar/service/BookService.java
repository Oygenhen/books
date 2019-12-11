package com.project.courses.bookbar.service;

import com.project.courses.bookbar.entity.Book;
import com.project.courses.bookbar.entity.User;
import com.project.courses.bookbar.repo.BookRepo;
import com.project.courses.bookbar.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;
    private final UserRepo userRepo;

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepo.findById(id);
    }

    public Book create(Book book) {
        return bookRepo.save(book);
    }

    public Book update(Book book) {
        return bookRepo.save(book);
    }

    public void delete(Book book) {
        bookRepo.delete(book);
    }

    public Book addBookToList(Long bookId, Long userId, String type) {
        Book book = bookRepo.findById(bookId).orElseThrow(EntityNotFoundException::new);
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        switch (type) {
            case "to-buy":
                book.add(book.getUsersWantToBuy(), user);
                break;
            case "to-read":
                book.add(book.getUsersWantToRead(), user);
                break;
            case "read":
                book.add(book.getUsersRead(), user);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return bookRepo.save(book);
    }

    public Book deleteBookFromList(Long bookId, Long userId, String type) {
        Book book = bookRepo.findById(bookId).orElseThrow(EntityNotFoundException::new);
        User user = userRepo.findById(userId).orElseThrow(EntityNotFoundException::new);
        switch (type) {
            case "to-buy":
                book.delete(book.getUsersWantToBuy(), user);
                break;
            case "to-read":
                book.delete(book.getUsersWantToRead(), user);
                break;
            case "read":
                book.delete(book.getUsersRead(), user);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return book;
    }
}
