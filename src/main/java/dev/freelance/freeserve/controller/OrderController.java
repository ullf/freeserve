package dev.freelance.freeserve.controller;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.repository.OrderRepository;
import dev.freelance.freeserve.service.AbstractOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class OrderController {

    private final AbstractOrderService abstractOrderService;

    @PostMapping("/createOrder")
    public ResponseEntity<AbstractOrder> createOrder(@RequestBody AbstractOrder ord) {
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
