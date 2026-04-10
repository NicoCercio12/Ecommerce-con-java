package com.ecommerce.ecommerce.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerce.ecommerce.DTO.UsuarioRequestDTO;
import com.ecommerce.ecommerce.DTO.UsuarioResponseDTO;
import com.ecommerce.ecommerce.model.Usuario;

public class UsuarioMapper {
    
    public static Usuario toEntity(UsuarioRequestDTO dto, PasswordEncoder passwordEncoder) {

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        return usuario;

    }

    public static UsuarioResponseDTO toDTO(Usuario usuario) {

        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setEmail(usuario.getEmail());

        return response;
    }

    public static void updateEntity(Usuario usuario, UsuarioRequestDTO dto) {

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());

    }
}
