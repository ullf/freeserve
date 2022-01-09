package dev.freelance.freeserve.config;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.service.AbstractClientService;
import io.jsonwebtoken.Jwts;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.security.Principal;

@Component
@NoArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private AbstractClientService abstractClientService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(header);
        if (header != null && header.startsWith("Bearer ")) {
            System.out.println("HEADER!!");
            System.out.println(SecurityContextHolder.getContext());
            var check = Jwts.parser().setSigningKey("ewUgbh93").parseClaimsJws(header.split(" ")[1].trim());
            System.out.println("Subject " +check.getBody());
            //log.log(Level.INFO,check.getBody().getSubject());
            UserDetails user = abstractClientService.loadUserByUsername(check.getBody().getSubject());
            //log.log(Level.INFO,user.getUsername());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(user,null,null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request,response);
        } else {
            filterChain.doFilter(request,response);
        }
       // System.out.println(header);
       
       // if(header == null){
           // SecurityContext sc = SecurityContextHolder.getContext();
            
           /* if (SecurityContextHolder.getContext().getAuthentication() == null) {
               // System.out.println(sc.getAuthentication() == null);
               System.out.println("NError!!!!");
               var tmp = request.getSession().getAttribute("Authorization");
               System.out.println("tmp "+tmp.toString().split(" ")[1]);
               if (tmp != null) {
                   request.setAttribute("Authorization", tmp.toString().split(" ")[1]);
               }
               filterChain.doFilter(request,response);
            } else {
                System.out.println("Error!!!!");
                filterChain.doFilter(request,response);
            }*/
          //  }
            //System.out.println("filter: "+header);
            //System.out.println(SecurityContextHolder.getContext());
            //  var check = Jwts.parser().setSigningKey("ewUgbh93").parseClaimsJws(header.split(" ")[1].trim());
            //l  System.out.println(check.getBody().getSubject());
            //filterChain.doFilter(request,response);
        }
        /*if (header != null && header.startsWith("Bearer ")) {
           System.out.println("header: "+header+" "+request.getHeader(HttpHeaders.HOST));
            var check = Jwts.parser().setSigningKey("ewUgbh93").parseClaimsJws(header.split(" ")[1].trim());
            System.out.println(check.getBody().getSubject());
            //log.log(Level.INFO,check.getBody().getSubject());
            UserDetails user = abstractClientService.loadUserByUsername(check.getBody().getSubject());
            //log.log(Level.INFO,user.getUsername());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(user,null,null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request,response);
        } else {
            filterChain.doFilter(request,response);
        }*/

    }
//}
