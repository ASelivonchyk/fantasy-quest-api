package dev.task.dndquest.model.entity;

import dev.task.dndquest.model.entity.advententity.Story;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "adventures")
public class Adventure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "story_line_id")
    private StoryLine storyLine;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "current_story_id")
    private Story currentStory;
    private int SceneCount;
    private int currentStoryPart;
}
