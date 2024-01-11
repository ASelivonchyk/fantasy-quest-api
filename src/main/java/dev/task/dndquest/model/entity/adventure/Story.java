package dev.task.dndquest.model.entity.adventure;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String placeType;
    private int storylinePart;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Creature> creatures;
}
