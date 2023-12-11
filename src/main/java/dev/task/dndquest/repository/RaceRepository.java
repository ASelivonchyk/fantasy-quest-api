package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.Race;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {
    Optional<Race> findByName(String name);
    Boolean existsByName(String name);
}
