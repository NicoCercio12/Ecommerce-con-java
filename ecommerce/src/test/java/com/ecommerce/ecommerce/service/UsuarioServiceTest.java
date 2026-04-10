package com.ecommerce.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerce.ecommerce.DTO.UsuarioRequestDTO;
import com.ecommerce.ecommerce.DTO.UsuarioResponseDTO;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repoUsuario;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UsuarioService service;

    @Test
    void deberiaAgregarUsuarioCorrectamente() {

        UsuarioRequestDTO dto = new UsuarioRequestDTO("Nico", "nico@mail.com", "1234");

        when(repoUsuario.existsByEmail(dto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("1234_encriptado");

        Usuario usuarioGuardado = new Usuario();
        usuarioGuardado.setId(1L);
        usuarioGuardado.setNombre("Nico");
        usuarioGuardado.setEmail("nico@mail.com");

        when(repoUsuario.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        UsuarioResponseDTO response = service.agregarUsuario(dto);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Nico", response.getNombre());
    }

    @Test
    void deberiaModificarUsuarioCorrectamente() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Viejo");
        usuario.setEmail("viejo@mail.com");

        UsuarioRequestDTO dto = new UsuarioRequestDTO("Nuevo", "nuevo@mail.com", "1234");

        when(repoUsuario.findById(1L)).thenReturn(Optional.of(usuario));
        when(repoUsuario.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioResponseDTO response = service.modificarUsuario(1L, dto);

        assertNotNull(response);
        assertEquals("Nuevo", response.getNombre());
        assertEquals("nuevo@mail.com", response.getEmail());
    }

    @Test
    void deberiaEliminarUsuarioCorrectamente() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(repoUsuario.findById(1L)).thenReturn(Optional.of(usuario));

        service.eliminarUsuario(1L);

        verify(repoUsuario).delete(usuario);
    }
}
