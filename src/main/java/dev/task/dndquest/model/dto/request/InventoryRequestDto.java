package dev.task.dndquest.model.dto.request;

import dev.task.dndquest.model.ItemOperations;
import dev.task.dndquest.validator.annotation.ItemConstraint;
import dev.task.dndquest.validator.annotation.EnumOperation;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InventoryRequestDto {
    @ItemConstraint
    private String item;
    @Min(value = 1, message = "{wrong.item.count}")
    private int count;
    @EnumOperation(enumClass = ItemOperations.class)
    private String operation;
}
