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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/api/login")
    public ResponseEntity<AbstractClient> login(HttpServletRequest request,@RequestBody AuthRequest authRequest) {
        if (authenticationManager == null) {
            return ResponseEntity.status(404).build();
        }
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(authRequest.getNickname(), authRequest.getPassword()));

            AbstractClient client = (AbstractClient) authentication.getPrincipal();
            String token = Jwts.builder().setIssuer("dev.freelance.freeserve").
                    setSubject(client.getNickname()).setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)).
                    signWith(SignatureAlgorithm.HS512, "ewUgbh93").compact();
            System.out.println(token);
            StringBuilder builder = new StringBuilder();
            var token2 = builder.append("Bearer ").append(token).toString();
            System.out.println(token2);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
            session.setMaxInactiveInterval(30);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token2).body(client);
        } catch (BadCredentialsException ex) {
            System.out.println("no");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
           // return ResponseEntity.status(404).build();
        }
    }

}
