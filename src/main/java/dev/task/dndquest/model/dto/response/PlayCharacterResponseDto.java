package dev.task.dndquest.model.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
    private List<InventoryResponseDto> items;
}
