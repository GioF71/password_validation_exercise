package it.sia.exercise.password_validator.controller;

import it.sia.exercise.password_validator.model.PasswordValidationData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RequestMapping("/passwordValidator")
public interface PasswordValidatorController {

    @PostMapping(value = "validatePasswords", produces = {TEXT_PLAIN_VALUE},consumes = {"text/plain;charset=UTF-8"})
    @ResponseBody
    String validatePasswords(@RequestBody String passwordValidatorControllers);

}
