package dev.freelance.freeserve.controller;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.service.AbstractOrderService;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
public class OrderController {

    private final AbstractOrderService abstractOrderService;

    @PostMapping("/createOrder")
    public ResponseEntity<AbstractOrder> createOrder(@RequestBody AbstractOrder ord, HttpServletRequest request) {
       // System.out.println("Principal: "+principal.getName());
       HttpSession session = request.getSession(true);
       System.out.println("Data: "+session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY)+"\n"+SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
      //  System.out.println("Sign: "+Jwts.parser().);
      var order = abstractOrderService.createOrder(ord);
      if(order != null) {
          return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/checkOrder/{orderId}")
    public ResponseEntity<AbstractOrder> checkOrder(@PathVariable int orderId) {
        var order = abstractOrderService.checkOrder(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/createOrder/{clientId}/{name}/{description}")
    public ResponseEntity<AbstractOrder> createOrder(@PathVariable int clientId,@PathVariable String name,@PathVariable String description) {
        var order = abstractOrderService.createOrder(clientId,name,description);
        if(order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

}
