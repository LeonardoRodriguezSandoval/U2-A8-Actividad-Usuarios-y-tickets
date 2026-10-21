package mx.edu.utez.tickets.service.ticket;

import jakarta.transaction.Transactional;
import mx.edu.utez.tickets.controller.ticket.dto.RequestCreateTicketDto;
import mx.edu.utez.tickets.controller.ticket.dto.RequestPrioridadDto;
import mx.edu.utez.tickets.exception.customExceptions.CustomBadException;
import mx.edu.utez.tickets.exception.customExceptions.CustomNotFoundException;
import mx.edu.utez.tickets.model.ticket.Estado;
import mx.edu.utez.tickets.model.ticket.Ticket;
import mx.edu.utez.tickets.model.usuario.Usuario;
import mx.edu.utez.tickets.repository.ticket.TicketRepository;
import mx.edu.utez.tickets.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    public TicketService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Ticket crearTicket(RequestCreateTicketDto payload) {
        Usuario usuario = buscarUsuario(payload.getUsuarioId());
        if (!usuario.isActivo()) {
            throw new CustomBadException("El usuario no esta activo");
        }
        Ticket ticket = new Ticket();
        ticket.setDescripcion(payload.getDescripcion());
        ticket.setEstado(Estado.ABIERTO);
        ticket.setPrioridad(payload.getPrioridad());
        ticket.setUsuario(usuario);
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket actualizarEstadoTicket(Long id) {
        Ticket ticket = buscarTicket(id);
        if (ticket.getEstado() == Estado.CERRADO) {
            throw new CustomBadException("El ticket esta cerrado y no se puede modificar");
        }
        if (ticket.getEstado() == Estado.ABIERTO) {
            ticket.setEstado(Estado.EN_PROCESO);
        } else {
            ticket.setEstado(Estado.CERRADO);
        }
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket reasignarPrioridad(Long id, RequestPrioridadDto payload) {
        Ticket ticket = buscarTicket(id);
        if (ticket.getEstado() == Estado.CERRADO) {
            throw new CustomBadException("El ticket esta cerrado y no se puede modificar");
        }
        ticket.setPrioridad(payload.getPrioridad());
        return ticketRepository.save(ticket);
    }

    public List<Ticket> obtenerTicketsUsuario(Long usuarioId) {
        buscarUsuario(usuarioId);
        return ticketRepository.findByUsuarioId(usuarioId);
    }

    private Usuario buscarUsuario(Long id) {
        Optional<Usuario> usuarioExist = usuarioRepository.findById(id);
        if (usuarioExist.isEmpty()) {
            throw new CustomNotFoundException("El usuario no existe");
        }
        return usuarioExist.get();
    }

    private Ticket buscarTicket(Long id) {
        Optional<Ticket> ticketExist = ticketRepository.findById(id);
        if (ticketExist.isEmpty()) {
            throw new CustomNotFoundException("El ticket no existe");
        }
        return ticketExist.get();
    }
}
