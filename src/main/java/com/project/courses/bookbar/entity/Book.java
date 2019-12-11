package com.project.courses.bookbar.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "BOOK")
@JsonIgnoreProperties({"usersRead","usersWantToRead", "usersWantToBuy"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(mappedBy = "readBooks")
    private Set<User> usersRead = new HashSet<>();

    @ManyToMany(mappedBy = "wantToReadBooks")
    private Set<User> usersWantToRead = new HashSet<>();

    @ManyToMany(mappedBy = "wantToBuyBooks")
    private Set<User> usersWantToBuy = new HashSet<>();

    public void add(Set<User> users, User user) {
        users.add(user);
    }

    public void delete(Set<User> users, User user) {
        users.remove(user);
    }
}
