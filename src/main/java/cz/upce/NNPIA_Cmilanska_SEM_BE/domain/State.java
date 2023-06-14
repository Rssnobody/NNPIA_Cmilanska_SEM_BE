package cz.upce.NNPIA_Cmilanska_SEM_BE.domain;

import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.StateOutputDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "STATES")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateId;
    @Column
    private String name;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "state")
    private List<UserBook> userBooks = Collections.emptyList();

    public StateOutputDto toDto() {
        return new StateOutputDto(
                getStateId(),
                getName()
        );
    }
}
