package dev.task.dndquest.model;

import dev.task.dndquest.strategy.item.AddItem;
import dev.task.dndquest.strategy.item.ItemStrategy;
import dev.task.dndquest.strategy.item.RemoveItem;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemOperations {
    ADD(new AddItem()),
    REMOVE(new RemoveItem());

    private final ItemStrategy itemStrategy;

    public static Map<String, ItemStrategy> getMap() {
        return Arrays.stream(ItemOperations.values())
                .collect(Collectors.toMap(
                        e -> e.name().toLowerCase(), ItemOperations::getItemStrategy));
    }
}
