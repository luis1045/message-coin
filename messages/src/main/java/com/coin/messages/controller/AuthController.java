package com.coin.messages.controller;

import com.coin.messages.model.Usuario;
import com.coin.messages.repository.UsuarioRepository;
import com.coin.messages.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UsuarioRepository repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthController(UsuarioRepository repo, JwtUtil jwtUtil, PasswordEncoder encoder) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRol("ROLE_USER");
        return repo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario user) {

        Usuario dbUser = repo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtUtil.generarToken(dbUser.getUsername());
    }
}