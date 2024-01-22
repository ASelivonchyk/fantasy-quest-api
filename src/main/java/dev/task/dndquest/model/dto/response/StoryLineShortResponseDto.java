package dev.task.dndquest.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryLineShortResponseDto {
    private String serial;
    private String title;
    private String description;
}
