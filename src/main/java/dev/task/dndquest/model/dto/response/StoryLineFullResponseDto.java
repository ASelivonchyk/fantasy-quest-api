package dev.task.dndquest.model.dto.response;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryLineFullResponseDto implements Serializable {
    private String serial;
    private String title;
    private String description;
    private List<StoryShortResponseDto> stories;
}
