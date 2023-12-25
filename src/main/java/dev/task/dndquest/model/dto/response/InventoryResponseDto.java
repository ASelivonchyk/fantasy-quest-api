package dev.task.dndquest.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InventoryResponseDto {
    private String name;
    private int count;
}
