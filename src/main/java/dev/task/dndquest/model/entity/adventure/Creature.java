package dev.task.dndquest.model.entity.adventure;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.Table;
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
