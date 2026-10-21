package mx.edu.utez.tickets.controller.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateUsuarioDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}
