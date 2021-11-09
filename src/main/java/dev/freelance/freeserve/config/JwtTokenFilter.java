package dev.freelance.freeserve.config;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@NoArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        //System.out.println(header);
        if (header.startsWith("Bearer ")) {
            System.out.println("header: "+header+" "+request.getHeader(HttpHeaders.HOST));
            System.out.println(Jwts.parser().parse(header).getBody().toString());
        }
        if(!header.startsWith("Bearer ")){
            System.out.println("filter: "+header);
          //  var check = Jwts.parser().setSigningKey("ewUgbh93").parseClaimsJws(header.split(" ")[1].trim());
          //  System.out.println(check.getBody().getSubject());
            filterChain.doFilter(request,response);
        }

    }
}
