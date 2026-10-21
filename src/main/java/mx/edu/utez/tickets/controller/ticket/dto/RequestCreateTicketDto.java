package mx.edu.utez.tickets.controller.ticket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.tickets.model.ticket.Prioridad;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateTicketDto {
    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria")
    private Prioridad prioridad;
}
