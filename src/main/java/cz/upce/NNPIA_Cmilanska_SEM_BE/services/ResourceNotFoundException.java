package cz.upce.NNPIA_Cmilanska_SEM_BE.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found")
public class ResourceNotFoundException extends Exception {
}
