package dev.freelance.freeserve.controller;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.entity.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import dev.freelance.freeserve.entity.ApiError;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request,@RequestBody AuthRequest authRequest) {
        if (authenticationManager == null) {
            return ResponseEntity.status(404).build();
        }
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(authRequest.getNickname(), authRequest.getPassword()));
            AbstractClient client = (AbstractClient) authentication.getPrincipal();
            String token = Jwts.builder().setIssuer("dev.freelance.freeserve").
                    setSubject(client.getNickname()).setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000*60*5)).
                    signWith(SignatureAlgorithm.HS512, "ewUgbh93").compact();
            return ResponseEntity.status(200).header(HttpHeaders.AUTHORIZATION, token).body(client);
        } catch (BadCredentialsException ex) {
            System.out.println("no");
            ApiError err = new ApiError();
            err.setMessage("Credentials are not correct");
            err.setStatus(HttpStatus.BAD_REQUEST);
            Optional<ApiError> op_err = Optional.of(err);
            return ResponseEntity.of(op_err);
        }
    }

}
