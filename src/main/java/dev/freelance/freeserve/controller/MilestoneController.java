package dev.freelance.freeserve.controller;

import dev.freelance.freeserve.entity.Milestone;
import dev.freelance.freeserve.service.MilestoneService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/createMilestone/{orderId}/{name}/{description}")
    public void createMilestone(@PathVariable int orderId,@PathVariable String name,@PathVariable String description) {
        milestoneService.createMilestone(orderId,name,description);
    }

    @PostMapping("/createMilestone")
    public ResponseEntity<Milestone> createMilestone(@RequestBody Milestone milestone) {
        int result = milestoneService.createMilestone(milestone);
        if (result == 0) {
            return ResponseEntity.ok(milestone);
        } else {
            return ResponseEntity.status(404).body(milestone);
        }
    }

    @GetMapping("/getAllMilestones/{id}")
    public ResponseEntity<List<Milestone>> createOrder(@PathVariable int id) {
        var milestone = milestoneService.getAllMilestonesByOrderId(id);
        if(milestone != null) {
            return ResponseEntity.ok(milestone);
        } else {
            return ResponseEntity.status(404).body(milestone);
        }
    }

    @GetMapping("/completeMilestone/{milestoneId}")
    public ResponseEntity<Integer> completeMilestone(@PathVariable int milestoneId) {
        int result = milestoneService.completeMilestone(milestoneId);
        if (result == 0) {
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }

}
