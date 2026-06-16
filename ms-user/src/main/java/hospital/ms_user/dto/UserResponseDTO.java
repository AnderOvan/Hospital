package hospital.ms_user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String email;
    private String primerNombre;
    private String segundoNombre; 
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono; 
    private String role;
    
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}