package mx.edu.utez.tickets.controller.ticket.dto;

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
public class RequestPrioridadDto {
    @NotNull(message = "La prioridad es obligatoria")
    private Prioridad prioridad;
}
