//package org.example.userservice.Controllers;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.example.userservice.dto.AuthRequest;
//import org.example.userservice.security.JwtUtil;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//    private AuthenticationManager authenticationManager;
//    private JwtUtil jwtUtil;
//
//    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody AuthRequest authRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//            );
//            String token = jwtUtil.generateToken(authRequest.getUsername());
//            return token;
//        }catch (AuthenticationException e) {
//            throw new RuntimeException("Identifiants invalides");
//        }
//
//    }
//}
