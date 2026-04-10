package com.ecommerce.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.DTO.*;
import com.ecommerce.ecommerce.mapper.UsuarioMapper;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.repository.UsuarioRepository;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service

public class UsuarioService {

    private final UsuarioRepository repoUsuario;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repoUsuario, PasswordEncoder passwordEncoder) {
        this.repoUsuario = repoUsuario;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO agregarUsuario(UsuarioRequestDTO dto) {

        if(repoUsuario.existsByEmail(dto.getEmail())) {

            throw new RuntimeException("El usuario ya existe. Error al agregar");

        }

        Usuario usuario = UsuarioMapper.toEntity(dto, passwordEncoder);
        Usuario guardado = repoUsuario.save(usuario);

        return UsuarioMapper.toDTO(guardado);
       
    }

    public Usuario traeUsuarioPorId(Long id) {

        return repoUsuario.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    }

    public List <Usuario> traerUsuarios() {

        return repoUsuario.findAll();
    }
    
    public UsuarioResponseDTO modificarUsuario(Long id, UsuarioRequestDTO dto) {

        Usuario usuarioModificar = repoUsuario.findById(id).orElseThrow(() -> new RuntimeException("El usuario no existe. Error al modificar"));

       UsuarioMapper.updateEntity(usuarioModificar, dto);
       Usuario actualizado = repoUsuario.save(usuarioModificar);

       return UsuarioMapper.toDTO(actualizado);

    }

    public void eliminarUsuario(Long id) {

        Usuario usuarioEliminar = repoUsuario.findById(id).orElseThrow(() -> new RuntimeException("El usuario no existe. Error al eliminar"));

        repoUsuario.delete(usuarioEliminar);

    }

       

    
}
