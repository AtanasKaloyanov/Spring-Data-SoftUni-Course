package com.example.gamestore.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.example.gamestore.constant.Validations.EMAIL_PATTERN;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    @Email(regexp = EMAIL_PATTERN)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column
    private Boolean isAdmine;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> games;

    @OneToMany(targetEntity = Order.class, mappedBy = "user",
            fetch = FetchType.EAGER)

    private Set<Order> orders;

    public User() {
        this.games = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public User(String email, String fullName, String password) {
        this();

        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Boolean getAdmine() {
        return isAdmine;
    }

    public void setAdmine(Boolean admine) {
        isAdmine = admine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        return Objects.equals(email, user.email)
                && Objects.equals(fullName, user.fullName)
                && Objects.equals(password, user.password)
                && Objects.equals(games, user.games);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, fullName, password, games);
    }
}
