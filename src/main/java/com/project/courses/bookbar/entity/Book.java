package com.project.courses.bookbar.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "book")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author);
    }
}
