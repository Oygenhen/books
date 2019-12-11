package com.project.courses.bookbar.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name ="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="login")
    private String login;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(fname, user.fname) &&
                Objects.equals(lname, user.lname) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, fname, lname, role);
    }

    @Column(name ="password")
    private String password;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @ManyToOne
    @JoinColumn(name ="role_id")
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "haveread_user_books",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private Set<Book> readBooks = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "wantread_user_books",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private Set<Book> wantToReadBooks = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "wantbuy_user_books",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private Set<Book> wantToBuyBooks = new HashSet<>();

    public void add(Set<Book> books, Book book) {
       books.add(book);
    }

    public void delete(Set<Book> books, Book book) {
        books.remove(book);
    }
}