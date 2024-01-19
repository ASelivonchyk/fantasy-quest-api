package dev.task.dndquest.model.entity;

import jakarta.persistence.*;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "character")
public class PlayCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private PlayCharacterClass playClass;
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    @ElementCollection
    @CollectionTable(name = "inventory",
            joinColumns = @JoinColumn(name = "character_id"))
    @MapKeyJoinColumn(name = "items_id")
    @Column(name = "items_count")
    private Map<Item, Integer> items;
}
