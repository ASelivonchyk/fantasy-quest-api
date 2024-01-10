package dev.task.dndquest.service;

import dev.task.dndquest.model.entity.advententity.Item;

public interface ItemService {
    Item findByName(String name);
    Boolean existsByName(String name);
}
