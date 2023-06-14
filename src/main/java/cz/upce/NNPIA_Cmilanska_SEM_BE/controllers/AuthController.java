package cz.upce.NNPIA_Cmilanska_SEM_BE.controllers;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.AuthRequest;
import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.AuthResponse;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.AppUserInputDto;
import cz.upce.NNPIA_Cmilanska_SEM_BE.security.JwtTokenProvider;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.AppUserDetailsService;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final AppUserService appUserService;
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtProvider;
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUserName(),
                            authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password.", e);
        }

        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(authRequest.getUserName());
        final String jwt = jwtProvider.createToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUserInputDto inputUser) {
        inputUser.setPassword(new BCryptPasswordEncoder().encode(inputUser.getPassword()));
        var result = appUserService.create(inputUser.toEntity());

        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(inputUser.getUserName());
        final String jwt = jwtProvider.createToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
