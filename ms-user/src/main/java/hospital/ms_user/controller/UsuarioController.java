package hospital.ms_user.controller;

import hospital.ms_user.dto.UserRequestDTO;
import hospital.ms_user.model.Usuario;

import hospital.ms_user.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UserRequestDTO requestDTO) {
        Usuario usuarioCreado = usuarioService.registrarUsuario(requestDTO);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }
}