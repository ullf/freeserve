package dev.freelance.freeserve.controller;

import dev.freelance.freeserve.service.MilestoneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/createMilestone/{orderId}/{name}/{description}")
    public void createMilestone(@PathVariable int orderId,@PathVariable String name,@PathVariable String description) {
        milestoneService.createMilestone(orderId,name,description);
    }

}
