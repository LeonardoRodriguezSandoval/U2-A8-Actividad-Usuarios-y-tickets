package mx.edu.utez.tickets.controller.ticket;

import jakarta.validation.Valid;
import mx.edu.utez.tickets.controller.ticket.dto.RequestCreateTicketDto;
import mx.edu.utez.tickets.controller.ticket.dto.RequestPrioridadDto;
import mx.edu.utez.tickets.model.ticket.Ticket;
import mx.edu.utez.tickets.service.ticket.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@RequestBody @Valid RequestCreateTicketDto payload) {
        return ResponseEntity.status(201).body(ticketService.crearTicket(payload));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Ticket> actualizarEstadoTicket(@PathVariable Long id) {
        return ResponseEntity.status(200).body(ticketService.actualizarEstadoTicket(id));
    }

    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<Ticket> reasignarPrioridad(@PathVariable Long id,
                                                     @RequestBody @Valid RequestPrioridadDto payload) {
        return ResponseEntity.status(200).body(ticketService.reasignarPrioridad(id, payload));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Ticket>> obtenerTicketsUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.status(200).body(ticketService.obtenerTicketsUsuario(usuarioId));
    }
}
