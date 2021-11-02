package dev.freelance.freeserve.entity;

import dev.freelance.freeserve.inter.OrderInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "abstract_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class AbstractOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "abstract_id")
    private int abstractId;
    private String abstractName;
    private String abstractDescription;
    @OneToMany
    private List<Milestone> milestones = new ArrayList<>();
    @ManyToOne
    //@JoinColumn(name= "id")
    private AbstractClient clientsId;
}
