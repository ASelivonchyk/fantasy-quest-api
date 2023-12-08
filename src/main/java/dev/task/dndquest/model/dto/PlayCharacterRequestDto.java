package dev.task.dndquest.model.dto;

import dev.task.dndquest.validator.CharacterClassConstraint;
import dev.task.dndquest.validator.PlayCharactersConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PlayCharactersConstraint
public class PlayCharacterRequestDto {
    @NotBlank(message = "{blank.name}")
    @Size(min = 3, max = 25, message = "{wrong.name}")
    private String name;
    @CharacterClassConstraint
    private String playCharClass;
    private String playCharRace;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
}
