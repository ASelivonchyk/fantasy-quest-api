package dev.task.dndquest.model.entity.adventure;

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
