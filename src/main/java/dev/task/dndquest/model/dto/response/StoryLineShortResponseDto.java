package dev.task.dndquest.model.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryLineShortResponseDto {
    private String serial;
    private String title;
    private String description;
}
