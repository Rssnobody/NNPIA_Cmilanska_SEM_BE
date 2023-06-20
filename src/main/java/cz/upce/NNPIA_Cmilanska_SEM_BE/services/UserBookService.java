package cz.upce.NNPIA_Cmilanska_SEM_BE.services;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.UserBook;
import cz.upce.NNPIA_Cmilanska_SEM_BE.repositories.IUserBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserBookService {
    private final IUserBookRepository userBookRepository;

    public UserBook findSpecificUserBook(Long userId, Long bookId) throws ResourceNotFoundException {
        var result = userBookRepository.findUserBookByUser_UserIdAndBook_BookId(userId, bookId);

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }

    public void create(final UserBook userBook) {
        userBookRepository.save(userBook);
    }

    @Transactional
    public void delete(Long userId, Long bookId) {
        userBookRepository.deleteUserBookByUser_UserIdAndBook_BookId(userId, bookId);
    }

    public UserBook updateUserBookState(final UserBook userBook) {
        return userBookRepository.save(userBook);
    }
}
