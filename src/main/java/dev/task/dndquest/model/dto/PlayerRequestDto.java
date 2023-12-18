package dev.task.dndquest.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerRequestDto {
    @Size(min = 3, max = 25, message = "{wrong.login}")
    private String login;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "{wrong.password}")
    private String password;
}
