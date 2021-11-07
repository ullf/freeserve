package dev.freelance.freeserve.controller;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.entity.AuthRequest;
import dev.freelance.freeserve.service.AbstractClientService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
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

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/api/login")
    public ResponseEntity<AbstractClient> login(@RequestBody AbstractClient abstractClient) {
        if (authenticationManager == null) {
            return ResponseEntity.status(404).build();
        }
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(abstractClient.getNickname(), abstractClient.getPassword()));

            AbstractClient client = (AbstractClient) authentication.getPrincipal();
            System.out.println("ok");
            String token = Jwts.builder().setIssuer("dev.freelance.freeserve").
                    setSubject(client.getNickname()).
                    signWith(SignatureAlgorithm.HS512, "ewUgbh93").compact();
            StringBuilder builder = new StringBuilder();
            token = builder.append("rQhrg565y5j37").append(token).toString();
            System.out.println(token);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).build();
        } catch (BadCredentialsException ex) {
            System.out.println("no");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
           // return ResponseEntity.status(404).build();
        }
    }

}
