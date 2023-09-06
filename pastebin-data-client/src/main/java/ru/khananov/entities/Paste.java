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
    private LocalDateTime created_at;

    @Column(name = "expiration_time")
    private Long expirationTimeSeconds;

    @Column
    private String payload;

    @Column(name = "access_modifier")
    @Enumerated(EnumType.STRING)
    private AccessModifier modifier;

    @Column(columnDefinition = "varchar(255) unique")
    private String hash;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private PasteUser user;

    public Paste() {
    }

    public Paste(Long id, LocalDateTime created_at, Long expirationTimeSeconds, String payload, AccessModifier modifier, String hash, PasteUser user) {
        this.id = id;
        this.created_at = created_at;
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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
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

    public PasteUser getUser() {
        return user;
    }

    public void setUser(PasteUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paste paste = (Paste) o;
        return Objects.equals(id, paste.id) && Objects.equals(created_at, paste.created_at) && Objects.equals(expirationTimeSeconds, paste.expirationTimeSeconds) && Objects.equals(payload, paste.payload) && modifier == paste.modifier && Objects.equals(hash, paste.hash) && Objects.equals(user, paste.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created_at, expirationTimeSeconds, payload, modifier, hash, user);
    }

    @Override
    public String toString() {
        return "Paste{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", expirationTimeSeconds=" + expirationTimeSeconds +
                ", payload='" + payload + '\'' +
                ", modifier=" + modifier +
                ", hash='" + hash + '\'' +
                ", user=" + user +
                '}';
    }
}