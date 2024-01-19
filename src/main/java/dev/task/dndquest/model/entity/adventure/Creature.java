package dev.task.dndquest.model.entity.adventure;

import jakarta.persistence.*;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "creatures")
public class Creature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String description;
    @ElementCollection
    @CollectionTable(name = "creature_interaction",
            joinColumns = @JoinColumn(name = "creature_id"))
    @MapKeyJoinColumn(name = "interaction_id")
    @Column(name = "interaction_result_id")
    private Map<Interaction, InteractionResult> interactions;
}
