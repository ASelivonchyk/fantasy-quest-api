package dev.task.dndquest.model.entity.advententity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class AdventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
