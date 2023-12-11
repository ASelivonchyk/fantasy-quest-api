package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.PlayCharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayCharacterClassRepository extends JpaRepository<PlayCharacterClass, Long> {
    Optional<PlayCharacterClass> findByName(String name);
    Boolean existsByName(String name);
}
