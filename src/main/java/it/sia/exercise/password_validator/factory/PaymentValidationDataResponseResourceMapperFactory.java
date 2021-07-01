package it.sia.exercise.password_validator.factory;

import it.sia.exercise.password_validator.model.PasswordValidationData;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentValidationDataResponseResourceMapperFactory
        implements ResourceMapperFactory<List<PasswordValidationData>,String> {

    @Override
    public String createModel(List<PasswordValidationData> dataToConvert) {
        List<String> validatedStrings = dataToConvert.stream().map(
                passwordValidationData -> passwordValidationData.getOriginalDataRequest()
                .concat(" - ".concat(passwordValidationData.getIsValid() ? "VALID" : "INVALID")))
                .collect(Collectors.toList());
        String returnString = "";
        for (String validatedString : validatedStrings) {
            returnString = returnString.concat(validatedString).concat(Strings.LINE_SEPARATOR);
        }

        return returnString;
    }

}
