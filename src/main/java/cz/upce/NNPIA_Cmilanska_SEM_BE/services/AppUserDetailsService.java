package cz.upce.NNPIA_Cmilanska_SEM_BE.services;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.AppUser;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IAppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final IAppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByLoginName(loginName);

        if (appUser == null) throw new UsernameNotFoundException("User not found with username: " + loginName);

        return new User(appUser.getLoginName(), appUser.getPassword(), Collections.emptyList());
    }
}