package dev.freelance.freeserve.controller;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.repository.OrderRepository;
import dev.freelance.freeserve.service.AbstractOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final AbstractOrderService abstractOrderService;

    @GetMapping("/createOrder/{clientId}/{name}/{description}")
    public void createOrder(@PathVariable int clientId,@PathVariable String name,@PathVariable String description) {
        abstractOrderService.createOrder(clientId,name,description);
    }

}
