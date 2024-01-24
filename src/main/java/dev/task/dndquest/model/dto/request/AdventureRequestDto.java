package dev.task.dndquest.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdventureRequestDto {
    private Long storyLineId;
    private Long currentStoryId;
}
