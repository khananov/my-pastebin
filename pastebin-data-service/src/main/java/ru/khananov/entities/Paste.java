package ru.khananov.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.khananov.entities.enums.AccessModifier;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "paste")
public class Paste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "expiration_time")
    private Long expirationTimeSeconds;

    @Column
    private String payload;

    @Column(name = "access_modifier")
    @Enumerated(EnumType.STRING)
    private AccessModifier modifier;

    @Column(columnDefinition = "varchar(255) unique")
    private String hash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Paste() {
    }

    public Paste(Long id, LocalDateTime createdAt, Long expirationTimeSeconds, String payload, AccessModifier modifier, String hash, User user) {
        this.id = id;
        this.createdAt = createdAt;
        this.expirationTimeSeconds = expirationTimeSeconds;
        this.payload = payload;
        this.modifier = modifier;
        this.hash = hash;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getExpirationTimeSeconds() {
        return expirationTimeSeconds;
    }

    public void setExpirationTimeSeconds(Long expirationTimeSeconds) {
        this.expirationTimeSeconds = expirationTimeSeconds;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public AccessModifier getModifier() {
        return modifier;
    }

    public void setModifier(AccessModifier modifier) {
        this.modifier = modifier;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paste paste = (Paste) o;
        return Objects.equals(id, paste.id) && Objects.equals(createdAt, paste.createdAt) && Objects.equals(expirationTimeSeconds, paste.expirationTimeSeconds) && Objects.equals(payload, paste.payload) && modifier == paste.modifier && Objects.equals(hash, paste.hash) && Objects.equals(user, paste.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, expirationTimeSeconds, payload, modifier, hash, user);
    }

    @Override
    public String toString() {
        return "Paste{" +
                "id=" + id +
                ", created_at=" + createdAt +
                ", expirationTimeSeconds=" + expirationTimeSeconds +
                ", payload='" + payload + '\'' +
                ", modifier=" + modifier +
                ", hash='" + hash + '\'' +
//                ", user=" + user +
                '}';
    }
}