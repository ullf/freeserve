package dev.freelance.freeserve.entity;

import dev.freelance.freeserve.inter.ClientInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AbstractClient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String surname;
    private boolean indicator; // true: freelancer false: buyer
    @OneToMany
    @JoinColumn(columnDefinition = "abstractId")
    private List<AbstractOrder> ordersId = new ArrayList<>();
}
