package cz.upce.NNPIA_Cmilanska_SEM_BE.controllers;

import cz.upce.NNPIA_Cmilanska_SEM_BE.domain.UserBook;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.UserBookInput;
import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.UserBookStateInputDto;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user-book")
@RestController
@AllArgsConstructor
public class UserBookController {
    private final UserBookService userBookService;
    private final AppUserService appUserService;
    private final BookService bookService;
    private final StateService stateService;
    private final ReviewService reviewService;

    @GetMapping(path = "/find/{userId}/{bookId}")
    public ResponseEntity<?> findSpecificUserBook(@PathVariable final Long userId, @PathVariable final Long bookId) {
        UserBook result;

        try {
            result = userBookService.findSpecificUserBook(userId, bookId);
        } catch (ResourceNotFoundException ex) {
            return null;
        }
        return ResponseEntity.ok(result.toDto());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUserBook(@RequestBody final UserBookInput input) throws ResourceNotFoundException {
        userBookService.create(toEntity(input));
        return ResponseEntity.ok(null);
    }

    @PostMapping("/update-state")
    public ResponseEntity<?> updateUserBookState(@RequestBody final UserBookStateInputDto input) throws ResourceNotFoundException {
        var result = userBookService.updateUserBookState(toEntityWithId(input));
        return ResponseEntity.ok(result.toDto());
    }

    @DeleteMapping(path="/delete/{userId}/{bookId}")
    public ResponseEntity<?> deleteByUserAndBook(@PathVariable final Long userId, @PathVariable final Long bookId) {
        userBookService.delete(userId, bookId);
        return ResponseEntity.ok(null);
    }

    public UserBook toEntityWithId(final UserBookStateInputDto input) throws ResourceNotFoundException {
        var review = input.getReviewId();
        return new UserBook(
                input.getUserBookId(),
                appUserService.findUserById(input.getUserId()),
                bookService.findBookById(input.getBookId()),
                stateService.findStateById(input.getStateId()),
                review == null ? null : reviewService.findReviewById(review)
        );
    }

    public UserBook toEntity(final UserBookInput input) throws ResourceNotFoundException {
        return new UserBook(
                appUserService.findUserById(input.getUserId()),
                bookService.findBookById(input.getBookId()),
                stateService.findStateById(input.getStateId()),
                null
        );
    }
}
