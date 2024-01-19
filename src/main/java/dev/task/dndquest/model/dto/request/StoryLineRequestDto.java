package dev.task.dndquest.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoryLineRequestDto {
    private String serial;
    private String title;
    private String description;
}
