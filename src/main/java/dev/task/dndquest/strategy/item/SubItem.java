package dev.task.dndquest.strategy.item;

public class SubItem implements ItemStrategy{
    @Override
    public Integer applyOperation(Integer itemsInInventoryCount, Integer itemsCount) {
        Integer resultCount = itemsInInventoryCount - itemsCount;
        if (resultCount < 0) {
            throw new IllegalArgumentException("not enough items");
        }
        return itemsInInventoryCount - itemsCount;
    }
}
