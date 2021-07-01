package it.sia.exercise.password_validator.model;

import lombok.*;

@Data
@Builder
public class PasswordValidationData {

    private Integer minOccur;

    private Integer maxOccur;

    private String validatingChar;

    private String stringToValidate;

    private Boolean isValid = false;

    private String originalDataRequest;

}
