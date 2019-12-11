package com.project.courses.bookbar.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name ="USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="login")
    private String login;

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
    @JoinTable(name = "HAVEREAD_USER_BOOKS",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "book_id", nullable = false, updatable = false)})
    private Set<Book> readBooks = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "WANTREAD_USER_BOOKS",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "book_id", nullable = false, updatable = false)})
    private Set<Book> wantToReadBooks = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "WANTBUY_USER_BOOKS",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "book_id", nullable = false, updatable = false)})
    private Set<Book> wantToBuyBooks = new HashSet<>();
}