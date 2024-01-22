package dev.task.dndquest.strategy.item;

public class AddItem implements ItemStrategy {
    @Override
    public Integer applyOperation(Integer itemsInInventoryCount, Integer itemsCount) {
        return itemsInInventoryCount + itemsCount;
    }
}
