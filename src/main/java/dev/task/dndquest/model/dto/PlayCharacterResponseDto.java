package dev.task.dndquest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlayCharacterResponseDto {
    private String name;
    private String playCharClassName;
    private String playCharRaceName;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private List<ItemResponseDto> items;
}
