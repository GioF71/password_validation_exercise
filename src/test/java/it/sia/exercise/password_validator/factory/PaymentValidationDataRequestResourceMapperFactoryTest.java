package it.sia.exercise.password_validator.factory;

import it.sia.exercise.password_validator.model.PasswordValidationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.List;

class PaymentValidationDataRequestResourceMapperFactoryTest {

    @Test
    public void testOK_ValidString_NoElements_ShouldBeMapped() {
        PaymentValidationDataRequestResourceMapperFactory paymentValidationDataResponseResourceMapperFactory
                = new PaymentValidationDataRequestResourceMapperFactory();
        List<PasswordValidationData> passwordValidationDataList =
                paymentValidationDataResponseResourceMapperFactory.createModel("");
        Assert.isTrue(passwordValidationDataList.isEmpty(), "should be empty");
    }

    @Test
    public void testOK_ValidString_OneElementValid_ShouldBeMapped() {
        PaymentValidationDataRequestResourceMapperFactory paymentValidationDataResponseResourceMapperFactory
                = new PaymentValidationDataRequestResourceMapperFactory();
        List<PasswordValidationData> passwordValidationDataList =
                paymentValidationDataResponseResourceMapperFactory.createModel("1-3 a: abcde");
        Assert.isTrue(!passwordValidationDataList.isEmpty(), "Result shouldn't be empty");
        Assert.isTrue(passwordValidationDataList.get(0).getMaxOccur().equals(3));
        Assert.isTrue(passwordValidationDataList.get(0).getMinOccur().equals(1));
        Assert.isTrue(passwordValidationDataList.get(0).getValidatingChar().equals("a"));
        Assert.isTrue(passwordValidationDataList.get(0).getOriginalDataRequest().equals("1-3 a: abcde"));
    }

}