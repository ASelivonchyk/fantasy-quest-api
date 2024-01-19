package dev.task.dndquest.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoryShortResponseDto {
    private String part;
    private String description;
    private String placeType;
    private String story;
}
