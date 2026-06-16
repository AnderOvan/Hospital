package hospital.ms_user.service.impl;

import hospital.ms_user.dto.UserRequestDTO;
import hospital.ms_user.mapper.UserMapper;
import hospital.ms_user.model.Usuario;
import hospital.ms_user.repository.UsuarioRepository;
import hospital.ms_user.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public Usuario registrarUsuario(UserRequestDTO dto) {
        // 1. Convertimos DTO a Entidad básica
        Usuario usuario = userMapper.toEntity(dto);

        // 2. Ejecutamos el algoritmo para generar el correo institucional único
        String correoGenerado = generarCorreoInstitucionalUnico(
                usuario.getPrimerNombre(), 
                usuario.getApellidoPaterno()
        );

        // 3. Asignamos el nuevo correo calculado al atributo de la entidad
        usuario.setCorreoInstitucional(correoGenerado);

        // 4. Guardamos en la Base de Datos
        return usuarioRepository.save(usuario);
    }

    /**
     * Algoritmo iterativo para construir correos institucionales sin duplicados.
     * Ejemplo secuencial si se registran varios "Juan Pérez":
     * 1. j.perez@hospital.com
     * 2. ju.perez@hospital.com
     * 3. jua.perez@hospital.com
     * 4. juan.perez@hospital.com
     * 5. juan.perez1@hospital.com ...
     */
    private String generarCorreoInstitucionalUnico(String primerNombre, String apellidoPaterno) {
        if (primerNombre == null || apellidoPaterno == null) {
            throw new IllegalArgumentException("El nombre y el apellido paterno son campos obligatorios.");
        }

        // Normalizamos los textos (quitar espacios, todo a minúsculas)
        String nombreLimpio = primerNombre.trim().toLowerCase().replaceAll("\\s+", "");
        String apellidoLimpio = apellidoPaterno.trim().toLowerCase().replaceAll("\\s+", "");
        String dominio = "@hospital.com";
        
        String correoPropuesto = "";
        int caracteresNombre = 1;

        // Fase 1: Intentar agregando letras del primer nombre de forma progresiva (j., ju., jua., juan.)
        while (caracteresNombre <= nombreLimpio.length()) {
            String prefijo = nombreLimpio.substring(0, caracteresNombre);
            correoPropuesto = prefijo + "." + apellidoLimpio + dominio;

            // Si no existe en la base de datos, lo tomamos de inmediato
            if (!usuarioRepository.existsByCorreoInstitucional(correoPropuesto)) {
                return correoPropuesto;
            }
            caracteresNombre++;
        }

        // Fase 2 (Failsafe): Si se agotan las letras (ej. llega un quinto "Juan Pérez")
        // Se añade un número incremental al final: juan.perez1@hospital.com, juan.perez2...
        String baseNombreCompleto = nombreLimpio + "." + apellidoLimpio;
        int contadorDuplicados = 1;

        do {
            correoPropuesto = baseNombreCompleto + contadorDuplicados + dominio;
            contadorDuplicados++;
        } while (usuarioRepository.existsByCorreoInstitucional(correoPropuesto));

        return correoPropuesto;
    }
}