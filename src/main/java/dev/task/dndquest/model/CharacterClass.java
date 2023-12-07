package dev.task.dndquest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CharacterClass {
    @Id
    private Long id;
    private String name;
}
