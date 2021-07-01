package it.sia.exercise.password_validator.service;

import it.sia.exercise.password_validator.model.PasswordValidationData;

import java.util.List;

public interface PasswordValidatorService {

    List<PasswordValidationData> validatePasswords(List<PasswordValidationData> passwordsToValidate);

}
