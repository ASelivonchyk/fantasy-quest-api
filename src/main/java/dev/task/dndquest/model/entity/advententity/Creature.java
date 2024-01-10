package dev.task.dndquest.model.entity.advententity;

import dev.task.dndquest.model.entity.Interaction;
import dev.task.dndquest.model.entity.InteractionResult;
import jakarta.persistence.*;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "creatures")
public class Creature extends AdventEntity {
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
