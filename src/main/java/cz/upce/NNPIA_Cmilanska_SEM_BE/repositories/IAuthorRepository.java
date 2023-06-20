package cz.upce.NNPIA_Cmilanska_SEM_BE.repositories;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO AUTHOR_BOOKS (author_id, book_id) VALUES (:authorId, :bookId)", nativeQuery = true)
    void createAuthorBook(Long authorId, Long bookId);
}
