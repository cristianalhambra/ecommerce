package com.tienda.ecommerce.auth.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Data Transfer Object (DTO) para manejar la solicitud de inicio de sesión.
 * Mapea los campos enviados desde el formulario de Angular.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LoginRequest {

    //Correo y contraseña para el usuario
    private String email;
    private String password;
}
