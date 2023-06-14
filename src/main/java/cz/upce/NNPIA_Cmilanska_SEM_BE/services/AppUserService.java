package cz.upce.NNPIA_Cmilanska_SEM_BE.services;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.AppUser;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IAppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AppUserService {
    private final IAppUserRepository appUserRepository;

    @Transactional(readOnly = true)
    public AppUser findUserById(Long id) throws ResourceNotFoundException {
        var result = appUserRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result.get();
    }

    public AppUser findByLoginName(String loginName) {
        return appUserRepository.findByLoginName(loginName);
    }

    @Transactional
    public AppUser create(final AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Transactional
    public AppUser update(final AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Transactional
    public void delete(final Long id) {
        appUserRepository.deleteById(id);
    }
}
