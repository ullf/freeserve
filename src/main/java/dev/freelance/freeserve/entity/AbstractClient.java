package dev.freelance.freeserve.entity;

import dev.freelance.freeserve.inter.ClientInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AbstractClient implements ClientInterface {
    private String name;
    private String surname;
    private boolean indicator;

    @Override
    public boolean isFreelancer() {
        return indicator == true;
    }

    @Override
    public boolean isBuyer() {
        return indicator == false;
    }
}
