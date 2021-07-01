package it.sia.exercise.password_validator.service;

import it.sia.exercise.password_validator.model.PasswordValidationData;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PasswordValidatorServiceImpl implements PasswordValidatorService {

    @Override
    public List<PasswordValidationData> validatePasswords(List<PasswordValidationData> passwordsToValidate) {
        for (PasswordValidationData passwordToValidate : passwordsToValidate) {
            long filteredListCount = Arrays.asList(passwordToValidate.getStringToValidate().split("")).stream().filter(
                    x -> {
                        return x.equals(passwordToValidate.getValidatingChar());
                    }).count();
            passwordToValidate.setIsValid(filteredListCount >= passwordToValidate.getMinOccur()
                    && filteredListCount <= passwordToValidate.getMaxOccur());
        }
        return passwordsToValidate;
    }
}
