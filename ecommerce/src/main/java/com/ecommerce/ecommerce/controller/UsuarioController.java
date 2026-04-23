package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.ecommerce.DTO.UsuarioRequestDTO;
import com.ecommerce.ecommerce.DTO.UsuarioResponseDTO;
import com.ecommerce.ecommerce.mapper.UsuarioMapper;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.service.UsuarioService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> agregarUsuario(@RequestBody UsuarioRequestDTO dto) {

        UsuarioResponseDTO response = service.agregarUsuario(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
 
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> modificarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto) {

        UsuarioResponseDTO response = service.modificarUsuario(id, dto);

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> traerUsuarios() {

        List<UsuarioResponseDTO> usuarios = service.traerUsuarios().stream().map(UsuarioMapper::toDTO).toList();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario>traerUsuarioPorId(@PathVariable Long id)  {
        
        Usuario usuario = service.traeUsuarioPorId(id);

        return ResponseEntity.ok(usuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {

        service.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }


}
