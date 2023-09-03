package ru.khananov.entyties;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.khananov.entyties.enums.AccessModifier;

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

    @Column
    private String hash;

    public Paste() {
    }

    public Paste(Long id, LocalDateTime created_at, Long expirationTimeSeconds, String payload, AccessModifier modifier, String hash) {
        this.id = id;
        this.created_at = created_at;
        this.expirationTimeSeconds = expirationTimeSeconds;
        this.payload = payload;
        this.modifier = modifier;
        this.hash = hash;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paste paste = (Paste) o;
        return Objects.equals(created_at, paste.created_at) && Objects.equals(expirationTimeSeconds, paste.expirationTimeSeconds) && Objects.equals(payload, paste.payload) && modifier == paste.modifier && Objects.equals(hash, paste.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created_at, expirationTimeSeconds, payload, modifier, hash);
    }

    @Override
    public String toString() {
        return "Paste{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", expirationInSeconds=" + expirationTimeSeconds +
                ", payload='" + payload + '\'' +
                ", modifier=" + modifier +
                ", hash='" + hash + '\'' +
                '}';
    }
}
