package dev.task.dndquest.model.dto;

import dev.task.dndquest.validator.annotation.CharacterClassConstraint;
import dev.task.dndquest.validator.annotation.PlayCharacterStatConstraint;
import dev.task.dndquest.validator.annotation.RaceConstraint;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayCharacterRequestDto {
    @Size(min = 3, max = 25, message = "{wrong.character.name}")
    private String name;
    @CharacterClassConstraint
    private String playCharClass;
    @RaceConstraint
    private String playCharRace;
    @PlayCharacterStatConstraint
    private int strength;
    @PlayCharacterStatConstraint
    private int dexterity;
    @PlayCharacterStatConstraint
    private int constitution;
    @PlayCharacterStatConstraint
    private int intelligence;
    @PlayCharacterStatConstraint
    private int wisdom;
    @PlayCharacterStatConstraint
    private int charisma;
}
