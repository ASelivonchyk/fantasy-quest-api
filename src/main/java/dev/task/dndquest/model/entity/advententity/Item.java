package dev.task.dndquest.model.entity.advententity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
public class Item extends AdventEntity {
    private String name;
}
