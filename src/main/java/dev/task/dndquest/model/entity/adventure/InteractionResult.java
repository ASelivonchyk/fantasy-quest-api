package dev.task.dndquest.model.entity.adventure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interaction_results")
public class InteractionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String luck; //success or failure
    private String action; // 
   // private AdventEntity result;
}
