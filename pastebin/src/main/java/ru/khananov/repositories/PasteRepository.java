package ru.khananov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khananov.entyties.Paste;

@Repository
public interface PasteRepository extends JpaRepository<Paste, Long> {
}
