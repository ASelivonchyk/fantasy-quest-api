package dev.task.dndquest.model.entity.character;

import dev.task.dndquest.model.entity.adventure.Adventure;
import dev.task.dndquest.model.entity.item.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.Table;
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
    @ElementCollection (fetch = FetchType.EAGER)
    @CollectionTable(name = "inventory",
            joinColumns = @JoinColumn(name = "character_id"))
    @MapKeyJoinColumn(name = "items_id")
    @Column(name = "items_count")
    private Map<Item, Integer> items;
    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "characters_games",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "adventure_id"))
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
