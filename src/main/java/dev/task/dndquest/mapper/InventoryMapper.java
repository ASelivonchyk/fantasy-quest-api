package dev.task.dndquest.mapper;

import dev.task.dndquest.model.dto.response.InventoryResponseDto;
import dev.task.dndquest.model.entity.item.Item;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper
abstract class InventoryMapper {
    public List<InventoryResponseDto> map(Map<Item, Integer> items) {
        return items.entrySet().stream()
                .map(e -> new InventoryResponseDto(e.getKey().getName(), e.getValue()))
                .collect(Collectors.toList());
    }
}
