package cz.upce.NNPIA_Cmilanska_SEM_BE.services;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Book;
import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.State;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IStateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StateService {
    private final IStateRepository stateRepository;

    @Transactional(readOnly = true)
    public State findStateById(Long id) throws ResourceNotFoundException {
        var result = stateRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result.get();
    }
}
