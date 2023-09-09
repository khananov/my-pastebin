package ru.khananov.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "paste_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Paste> pastes;

    public User() {
    }

    public User(Long id, String email, String password, List<Paste> pastes) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.pastes = pastes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Paste> getPastes() {
        return pastes;
    }

    public void setPastes(List<Paste> pastes) {
        this.pastes = pastes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(pastes, user.pastes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, pastes);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}