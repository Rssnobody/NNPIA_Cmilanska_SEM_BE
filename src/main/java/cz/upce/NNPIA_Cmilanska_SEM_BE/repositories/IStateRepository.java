package cz.upce.NNPIA_Cmilanska_SEM_BE.repositories;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStateRepository extends JpaRepository<State, Long> {
}
