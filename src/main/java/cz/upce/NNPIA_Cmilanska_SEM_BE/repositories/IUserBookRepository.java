package cz.upce.NNPIA_Cmilanska_SEM_BE.repositories;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserBookRepository extends JpaRepository<UserBook, Long> {
    UserBook findUserBookByUser_UserIdAndBook_BookId(Long userId, Long bookId);

    void deleteUserBookByUser_UserIdAndBook_BookId(Long userId, Long bookId);
}
