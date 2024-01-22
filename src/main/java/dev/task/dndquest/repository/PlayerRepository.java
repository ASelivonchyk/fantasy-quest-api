package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.Player;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByLogin(String login);

    Boolean existsByLogin(String login);
}
