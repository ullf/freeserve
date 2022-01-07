package dev.freelance.freeserve.controller;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.service.AbstractClientService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import dev.freelance.freeserve.entity.ApiError;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ClientController {

    private final AbstractClientService abstractClientService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/createClient")
    public void createAbstractClient(@RequestBody AbstractClient abstractClient) {
        abstractClient.setPassword(passwordEncoder.encode(abstractClient.getPassword()));
        abstractClientService.createAbstractClient(abstractClient);
    }

    @GetMapping("/createClient/{name}/{surname}/{bool}")
    public void createAbstractClient(@PathVariable String name,@PathVariable String surname,@PathVariable boolean bool) {
        abstractClientService.createAbstractClient(name,surname,bool);
    }

    @GetMapping("/findClientByNickname/{nickname}")
    public ResponseEntity<?> findClientByNickname(@PathVariable String nickname) {
        var user = abstractClientService.findAbstractClientByNickname(nickname);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        ApiError err = new ApiError();
        err.setMessage("No client found with such nickname");
        err.setStatus(HttpStatus.NOT_FOUND);
        Optional<ApiError> op_err = Optional.of(err);
        return ResponseEntity.of(op_err);
    }

}
