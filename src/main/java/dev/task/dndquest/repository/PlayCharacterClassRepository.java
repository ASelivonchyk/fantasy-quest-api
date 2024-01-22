package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.character.PlayCharacterClass;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayCharacterClassRepository extends JpaRepository<PlayCharacterClass, Long> {
    Optional<PlayCharacterClass> findByName(String name);

    Boolean existsByName(String name);
}
