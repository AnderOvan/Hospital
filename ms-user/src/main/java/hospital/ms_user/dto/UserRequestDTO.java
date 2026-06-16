package hospital.ms_user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para recibir los datos de creación o actualización de un usuario.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe proporcionar un formato de email válido")
    private String email;

    @NotBlank(message = "El primer nombre es obligatorio")
    private String primerNombre;

    // No todos tienen un segundo nombre
    private String segundoNombre;

    @NotBlank(message = "El apellido paterno es obligatorio")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    private String apellidoMaterno;

    @NotBlank(message = "El número de teléfono es obligatorio")
    private String telefono;

    // Puedes incluir roles o tipos de usuario si el ms-user los maneja
    private String role;
}
