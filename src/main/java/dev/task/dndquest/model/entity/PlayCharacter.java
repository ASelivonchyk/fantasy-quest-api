package dev.task.dndquest.model.entity;

import dev.task.dndquest.model.entity.advententity.Item;
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
    @ManyToOne
    @JoinTable(
            name="characters_games",
            joinColumns=@JoinColumn(name="character_id"),
            inverseJoinColumns=@JoinColumn(name="adventure_id"))
    private Adventure adventure;

    public PlayCharacter(String name, PlayCharacterClass playClass, Race race,
                         int strength, int dexterity, int constitution,
                         int intelligence, int wisdom, int charisma) {
        this.name = name;
        this.playClass = playClass;
        this.race = race;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }
}
