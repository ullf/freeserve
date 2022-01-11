package dev.freelance.freeserve.controller;

import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.entity.ApiError;
import dev.freelance.freeserve.service.AbstractOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class OrderController {

    private final AbstractOrderService abstractOrderService;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody AbstractOrder ord, HttpServletRequest request) {
      var order = abstractOrderService.createOrder(ord);
      if(order.getAbstractName() != null && !order.getClientsId().getNickname().equals(null)) {
          return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.badRequest().body("Unknown error,authentication could be needed");
        }
    }

    @PostMapping("/takeOrder/{orderId}")
    public ResponseEntity<?> takeOrder(@PathVariable int orderId) {
        var order = abstractOrderService.takeOrder(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(404).body("No order found with such order id");
        }
    }

    @GetMapping("/getTakenOrders/{clientId}")
    public ResponseEntity<List<?>> getTakenOrders(@PathVariable int clientId) {
        var orders = abstractOrderService.getTakenOrders(clientId);
        if (orders.size() != 0) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.status(404).body(orders);
        }
    }

    @GetMapping("/checkOrder/{orderId}")
    public ResponseEntity<?> checkOrder(@PathVariable int orderId) {
        var order = abstractOrderService.checkOrder(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            ApiError err = new ApiError();
            err.setMessage("No order found with such order id");
            err.setStatus(HttpStatus.NOT_FOUND);
            Optional<ApiError> op_err = Optional.of(err);
            return ResponseEntity.of(op_err);
        }

        
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

    @GetMapping("/getAllOrdersByClientId/{id}")
    public ResponseEntity<List<?>> createOrder(@PathVariable int id) {
        var orders = abstractOrderService.getAllOrdersById(id);
        if(orders.size() != 0) {
            return ResponseEntity.ok(orders);
        } else {
            ApiError err = new ApiError();
            err.setMessage("No orders found with such client id");
            err.setStatus(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(404).body(List.of(err));
        }
    }

    @GetMapping("/completeOrder/{orderId}")
    public ResponseEntity<?> completeOrder(@PathVariable int orderId) {
        int result = abstractOrderService.completeOrder(orderId);
        if (result == 0) {
            return ResponseEntity.status(200).body(result);
        } else {
            ApiError err = new ApiError();
            err.setMessage("No order found with such order id");
            err.setStatus(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(404).body(err);
        }
       
    }
}
