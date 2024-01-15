package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.adventure.StoryLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryLineRepository extends JpaRepository<StoryLine, Long> {
}
