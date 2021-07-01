package it.sia.exercise.password_validator.factory;

import it.sia.exercise.password_validator.model.PasswordValidationData;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentValidationDataRequestResourceMapperFactory
        implements ResourceMapperFactory<String, List<PasswordValidationData>> {

    @Override
    public List<PasswordValidationData> createModel(String stringToConvert) {

        List<PasswordValidationData> passwordValidationList = new ArrayList<>();
        String[] linesToConvert = stringToConvert.split(Strings.LINE_SEPARATOR);

        if (!stringToConvert.isEmpty()) {

            for (String lineToConvert : linesToConvert) {
                String[] passwordArray = lineToConvert.split(":", 2);

                PasswordValidationData passwordValidationData = PasswordValidationData.builder().build();
                String[] validatorData = passwordArray[0].split(" ");
                String[] occursData = validatorData[0].split("-");

                passwordValidationData.setMinOccur(Integer.valueOf(occursData[0]));

                if (occursData.length > 1) {
                    passwordValidationData.setMaxOccur(Integer.valueOf(occursData[1]));
                }

                if (validatorData.length > 1) {
                    passwordValidationData.setValidatingChar(validatorData[1].trim());
                }

                if (passwordArray.length > 1) {
                    passwordValidationData.setStringToValidate(passwordArray[1].trim());
                }

                passwordValidationData.setOriginalDataRequest(lineToConvert);

                passwordValidationList.add(passwordValidationData);

            }
        }

        return passwordValidationList;
    }

}
