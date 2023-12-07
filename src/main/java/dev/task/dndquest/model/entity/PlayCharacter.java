package dev.task.dndquest.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class PlayCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Enter character name")
    @Size(min = 3, max = 50, message = "Name should be greater than 3 letter and shorter than 50")
    private String name;
    @ManyToOne
    private PlayCharacterClass clas;
    @ManyToOne
    private Race race;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    public PlayCharacter(String name, PlayCharacterClass clas, Race race,
                         int strength, int dexterity, int constitution, int intelligence,
                         int wisdom, int charisma) {
        this.name = name;
        this.clas = clas;
        this.race = race;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }
}
