package dev.task.dndquest.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoryRequestDto {
    private int part;
    private String description;
    private String placeType;
    private String story;
}
