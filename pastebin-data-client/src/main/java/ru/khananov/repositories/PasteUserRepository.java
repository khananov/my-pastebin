package ru.khananov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khananov.entities.PasteUser;

@Repository
public interface PasteUserRepository extends JpaRepository<PasteUser, Long> {
}
