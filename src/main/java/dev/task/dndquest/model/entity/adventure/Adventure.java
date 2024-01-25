package dev.task.dndquest.model.entity.adventure;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "adventures")
public class Adventure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "story_line_id", referencedColumnName = "id")
    private StoryLine storyLine;
    @OneToOne
    @JoinColumn(name = "current_story_id", referencedColumnName = "id")
    private Story currentStory;
    private int sceneCount;
    private int currentStoryPart;

    public Adventure(StoryLine storyLine) {
        this.storyLine = storyLine;
    }
}
