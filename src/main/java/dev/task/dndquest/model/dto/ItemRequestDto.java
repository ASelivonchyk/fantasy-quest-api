package dev.task.dndquest.model.dto;

import dev.task.dndquest.validator.annotation.ItemConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor
public class ItemRequestDto {
    @ItemConstraint
    private String name;
    @Range(min = 1, message = "{wrong.item.count}")
    private int count;
}
