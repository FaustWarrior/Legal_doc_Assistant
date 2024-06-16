package com.example.legaldocassistant.controller;

import com.example.legaldocassistant.model.User;
import com.example.legaldocassistant.security.JwtUtil;
import com.example.legaldocassistant.service.CustomUserDetailsService;
import com.example.legaldocassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        logger.info("Registration request received: {}", authRequest);
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(authRequest.getPassword());
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        logger.info("AuthRequest received: {}", authRequest);
        try {
            logger.info("Attempting authentication for user: {}", authRequest.getUsername());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            logger.info("Authentication successful for user: {}", authRequest.getUsername());
        } catch (BadCredentialsException e) {
            logger.error("Invalid username or password for user: {}", authRequest.getUsername(), e);
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        logger.info("JWT token generated for user: {}", authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    public static class AuthRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "AuthRequest{" +
                    "username='" + username + '\'' +
                    ", password='[PROTECTED]'}";
        }
    }
    
    public static class AuthResponse {
        private final String jwt;

        public AuthResponse(String jwt) {
            this.jwt = jwt;
        }

        public String getJwt() {
            return jwt;
        }
    }
}
