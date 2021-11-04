package dev.freelance.freeserve.controller;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.service.AbstractClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientController {

    private final AbstractClientService abstractClientService;

    @GetMapping("/createClient/{name}/{surname}/{bool}")
    public void createAbstractClient(@PathVariable String name,@PathVariable String surname,@PathVariable boolean bool) {
        abstractClientService.createAbstractClient(name,surname,bool);
    }

    @GetMapping("/findUser/{nickname}")
    public ResponseEntity<AbstractClient> findByNickname(@PathVariable String nickname) {
        var user = abstractClientService.findByNickname(nickname);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return null;
    }

}
