package cz.upce.NNPIA_Cmilanska_SEM_BE.repositories;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepository  extends JpaRepository<AppUser, Long> {
    AppUser findByLoginName(String loginName);
}