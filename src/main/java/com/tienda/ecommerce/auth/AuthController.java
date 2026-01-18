package com.tienda.ecommerce.auth;

import com.tienda.ecommerce.auth.dto.LoginRequest;
import com.tienda.ecommerce.auth.dto.RegisterRequest;
import com.tienda.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST para manejar las operaciones de autenticación (Registro e Inicio de Sesión).
 */
@RestController
@RequestMapping("/api/v1/auth")
// Importante: Dejamos "*" temporalmente para descartar problemas de CORS durante las pruebas
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("Recibida petición de registro para: " + request.getEmail());

            if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
                response.put("message", "Datos de registro incompletos");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            User user = authService.register(request);

            response.put("message", "Usuario registrado exitosamente");
            response.put("userId", user.getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            System.err.println("Error en registro: " + e.getMessage());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        return authService.login(request)
                .map(user -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Inicio de sesión exitoso");
                    response.put("name", user.getName());
                    response.put("token", "simulated-jwt-token-for-" + user.getEmail());
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, Object> errorResponse = new HashMap<>();
                    errorResponse.put("message", "Credenciales incorrectas");
                    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
                });
    }
}