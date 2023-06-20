package cz.upce.NNPIA_Cmilanska_SEM_BE.controllers;

import cz.upce.NNPIA_Cmilanska_SEM_BE.dtos.AppUserOutputDto;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/app-user")
@RestController
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping(path="/find/{loginName}")
    public ResponseEntity<AppUserOutputDto> findByUserName(@PathVariable String loginName) {
        var result = appUserService.findByLoginName(loginName);
        return ResponseEntity.ok(result.toDto());
    }
}
