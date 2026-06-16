package hospital.ms_user.service;

import hospital.ms_user.dto.UserRequestDTO;
import hospital.ms_user.model.Usuario;

public interface UsuarioService {
    Usuario registrarUsuario(UserRequestDTO userRequestDTO);
}