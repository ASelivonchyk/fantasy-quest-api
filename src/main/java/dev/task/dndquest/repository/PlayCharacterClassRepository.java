package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.PlayCharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayCharacterClassRepository extends JpaRepository<PlayCharacterClass, Long> {
    Optional<PlayCharacterClass> findByName(String name);
}
