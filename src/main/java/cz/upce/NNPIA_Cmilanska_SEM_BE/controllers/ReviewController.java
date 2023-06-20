package cz.upce.NNPIA_Cmilanska_SEM_BE.controllers;

import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.ReviewInput;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.ResourceNotFoundException;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/review")
@RestController
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<?> createReview(@RequestBody final ReviewInput input) throws ResourceNotFoundException {
        var result = reviewService.createReview(input);
        return ResponseEntity.ok(result);
    }
}
