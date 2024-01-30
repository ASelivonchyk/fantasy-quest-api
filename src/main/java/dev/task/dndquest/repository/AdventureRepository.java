package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.adventure.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {
}
