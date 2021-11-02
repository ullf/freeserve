package dev.freelance.freeserve.controller;

import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.entity.Milestone;
import dev.freelance.freeserve.repository.MilestoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MilestoneController {

    private final MilestoneRepository milestoneRepository;

    public void createMilestone(@PathVariable String name,@PathVariable String description) {
        Milestone milestone = new Milestone();
        milestone.setMilestoneName(name);
        milestone.setMilestoneDescription(description);
        milestoneRepository.save(milestone);
    }

}
