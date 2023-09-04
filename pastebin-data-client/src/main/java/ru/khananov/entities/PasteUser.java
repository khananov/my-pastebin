package ru.khananov.entities;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "paste_users")
public class PasteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private char[] password;

    @OneToMany(mappedBy = "user")
    private List<Paste> pastes;

    public PasteUser() {
    }

    public PasteUser(Long id, String email, char[] password, List<Paste> pastes) {
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

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
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
        PasteUser pasteUser = (PasteUser) o;
        return Objects.equals(id, pasteUser.id) && Objects.equals(email, pasteUser.email) && Arrays.equals(password, pasteUser.password) && Objects.equals(pastes, pasteUser.pastes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, email, pastes);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    @Override
    public String toString() {
        return "PasteUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password=" + Arrays.toString(password) +
                ", pastes=" + pastes +
                '}';
    }
}