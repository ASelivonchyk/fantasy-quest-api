package dev.task.dndquest.repository;

import dev.task.dndquest.model.entity.item.Item;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);

    Boolean existsByName(String name);
}
