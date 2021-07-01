package it.sia.exercise.password_validator.service;

import it.sia.exercise.password_validator.model.PasswordValidationData;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorServiceImplTest {


    @Test
    public void testOK_VoidList_ShouldReturnVoidList() {
        PasswordValidatorService passwordValidatorService = new PasswordValidatorServiceImpl();
        List<PasswordValidationData> result = passwordValidatorService.validatePasswords(Collections.emptyList());
        Assert.isTrue(result.isEmpty(), "List should be empty");
    }

    @Test
    public void testOK_OneElement_ShouldReturnValidOnList() {
        PasswordValidatorService passwordValidatorService = new PasswordValidatorServiceImpl();
        List<PasswordValidationData> result = passwordValidatorService.validatePasswords(
                Collections.singletonList(PasswordValidationData
                        .builder()
                        .minOccur(1)
                        .maxOccur(3)
                        .validatingChar("a")
                        .stringToValidate("abcde")
                        .originalDataRequest("1-3 a: abcde")
                        .isValid(false)
                        .build()));
        Assert.isTrue(!result.isEmpty(),"list shouldn't be empty");
        Assert.isTrue(result.get(0).getIsValid(),"element should be valid");
    }

    @Test
    public void testOK_OneElement_MissingChar_ShouldReturnInvalidOnList() {
        PasswordValidatorService passwordValidatorService = new PasswordValidatorServiceImpl();
        List<PasswordValidationData> result = passwordValidatorService.validatePasswords(
                Collections.singletonList(PasswordValidationData
                        .builder()
                        .minOccur(1)
                        .maxOccur(3)
                        .validatingChar("f")
                        .stringToValidate("abcde")
                        .originalDataRequest("1-3 f: abcde")
                        .isValid(false)
                        .build()));
        Assert.isTrue(!result.isEmpty(),"list shouldn't be empty");
        Assert.isTrue(!result.get(0).getIsValid(),"element shouldn't be valid");
    }


}