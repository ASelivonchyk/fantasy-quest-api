package dev.task.dndquest.model.dto.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryLineShortResponseDto implements Serializable {
    private String serial;
    private String title;
    private String description;
}
