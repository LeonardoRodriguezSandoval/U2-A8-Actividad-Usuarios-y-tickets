package mx.edu.utez.tickets.service.usuario;

import jakarta.transaction.Transactional;
import mx.edu.utez.tickets.controller.usuario.dto.RequestCreateUsuarioDto;
import mx.edu.utez.tickets.exception.customExceptions.CustomBadException;
import mx.edu.utez.tickets.exception.customExceptions.CustomNotFoundException;
import mx.edu.utez.tickets.model.usuario.Usuario;
import mx.edu.utez.tickets.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario crearUsuario(RequestCreateUsuarioDto payload) {
        Usuario usuario = new Usuario();
        usuario.setNombre(payload.getNombre());
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario desactivarUsuario(Long id) {
        Optional<Usuario> usuarioExist = usuarioRepository.findById(id);
        if (usuarioExist.isEmpty()) {
            throw new CustomNotFoundException("El usuario no existe");
        }
        Usuario usuario = usuarioExist.get();
        if (!usuario.isActivo()) {
            throw new CustomBadException("El usuario ya esta desactivado");
        }
        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }
}
