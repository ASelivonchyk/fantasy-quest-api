package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.PlayCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayCharacterRepository extends JpaRepository<PlayCharacter, Long> {
}
