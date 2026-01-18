package com.tienda.ecommerce.auth;

import com.tienda.ecommerce.auth.dto.LoginRequest;
import com.tienda.ecommerce.auth.dto.RegisterRequest;
import com.tienda.ecommerce.model.User;
import com.tienda.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio encargado de la lógica de negocio para la autenticación.
 */
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // PENDIENTE: Inyectar BCryptPasswordEncoder cuando configuremos Security

    /**
     * Registra un nuevo usuario en el sistema.
     */
    public User register(RegisterRequest request) throws Exception {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new Exception("El email ya está registrado");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        // IMPORTANTE: Aquí se debería encriptar la contraseña
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

    /**
     * Valida las credenciales de un usuario.
     */
    public Optional<User> login(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(user -> user.getPassword().equals(request.getPassword()));
        // En el futuro, usar passwordEncoder.matches()
    }
}