package dev.task.dndquest.model.entity.adventure;

import dev.task.dndquest.model.entity.adventure.Story;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "storylines")
public class StoryLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @OneToMany
    private List<Story> stories;
}
