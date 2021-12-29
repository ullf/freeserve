package dev.freelance.freeserve.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import dev.freelance.freeserve.entity.AbstractClient;

@Component
public class AuthHandler implements AuthenticationSuccessHandler {

    //@Autowired
    //ActiveUserStore activeUserStore;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) 
      throws IOException {
        HttpSession session = request.getSession(false);
        System.out.println("neee!");
        if (session != null) {
            AbstractClient client =  (AbstractClient)authentication.getPrincipal();
            session.setAttribute(client.getName(), client);
            System.out.println("eee!");
        }
    }
}