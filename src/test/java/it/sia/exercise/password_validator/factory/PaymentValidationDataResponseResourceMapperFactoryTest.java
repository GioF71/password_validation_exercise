package it.sia.exercise.password_validator.factory;

import it.sia.exercise.password_validator.model.PasswordValidationData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PaymentValidationDataResponseResourceMapperFactoryTest {

    @Test
    public void testOK_ValidString_NoElements_ShouldBeMapped() {
        PaymentValidationDataResponseResourceMapperFactory paymentValidationDataResponseResourceMapperFactory
                = new PaymentValidationDataResponseResourceMapperFactory();
        String result = paymentValidationDataResponseResourceMapperFactory.createModel(Collections.emptyList());
        Assert.isTrue(result.isEmpty(), "Result should be empty");
    }

    @Test
    public void testOK_ValidString_OneElementValid_ShouldBeMapped() {
        PaymentValidationDataResponseResourceMapperFactory paymentValidationDataResponseResourceMapperFactory
                = new PaymentValidationDataResponseResourceMapperFactory();
        String result = paymentValidationDataResponseResourceMapperFactory.createModel(Collections.singletonList(
                PasswordValidationData.builder().isValid(true).originalDataRequest("testData").build()));
        Assert.isTrue(result.equals("testData - VALID\r\n"),"result should contain VALID");

    }

    @Test
    public void testOK_ValidString_OneElementInvalid_ShouldBeMapped() {
        PaymentValidationDataResponseResourceMapperFactory paymentValidationDataResponseResourceMapperFactory
                = new PaymentValidationDataResponseResourceMapperFactory();
        String result = paymentValidationDataResponseResourceMapperFactory.createModel(Collections.singletonList(
                PasswordValidationData.builder().isValid(false).originalDataRequest("testData").build()));
        Assert.isTrue(result.equals("testData - INVALID\r\n"), "result should contain INVALID");
    }

}