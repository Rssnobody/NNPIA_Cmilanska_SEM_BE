package cz.upce.NNPIA_Cmilanska_SEM_BE.repositories;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    Book findBookByBookId(Long id);

    Book findBookByName(String name);

    @Query(
    """
    SELECT books
    FROM Book books
    JOIN UserBook ub on books.bookId = ub.book.bookId
    WHERE ub.user.userId = :userId
    """)
    List<Book> findBooksOfSpecifiedUser(Long userId);

    @Query(
            """
            SELECT books
            FROM Book books
            JOIN UserBook ub on books.bookId = ub.book.bookId
            WHERE ub.user.userId = :userId
            AND ub.state.stateId = :state
            """)
    List<Book> findBooksByStateOfSpecifiedUser(Long userId, Long state);
}
