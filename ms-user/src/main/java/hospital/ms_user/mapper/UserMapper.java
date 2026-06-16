package hospital.ms_user.mapper;

import hospital.ms_user.dto.UserRequestDTO;
import hospital.ms_user.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public Usuario toEntity(UserRequestDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail()); // Asigna el correo personal que viene en el JSON
        usuario.setPrimerNombre(dto.getPrimerNombre());
        usuario.setSegundoNombre(dto.getSegundoNombre());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRole(dto.getRole());

        // Dejamos el campo 'correoInstitucional' en blanco para que lo resuelva el Service
        return usuario;
    }
}