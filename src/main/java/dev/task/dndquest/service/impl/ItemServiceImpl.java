package dev.task.dndquest.service.impl;

import dev.task.dndquest.exception.ItemNotFoundException;
import dev.task.dndquest.model.entity.Item;
import dev.task.dndquest.repository.ItemRepository;
import dev.task.dndquest.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;

    @Override
    public Item findByName(String name) {
        return repository.findByName(name).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public Boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
