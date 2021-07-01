package it.sia.exercise.password_validator.controller;

import it.sia.exercise.password_validator.factory.ResourceMapperFactory;
import it.sia.exercise.password_validator.model.PasswordValidationData;
import it.sia.exercise.password_validator.service.PasswordValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PasswordValidatorControllerImpl implements PasswordValidatorController {

    private final PasswordValidatorService passwordValidatorService;
    private final ResourceMapperFactory<String,List<PasswordValidationData>> stringListResourceMapperFactory;
    private final ResourceMapperFactory<List<PasswordValidationData>,String> listStringResourceMapperFactory;

    @Autowired
    public PasswordValidatorControllerImpl(
            PasswordValidatorService passwordValidatorService,
            ResourceMapperFactory<String,List<PasswordValidationData>> stringListResourceMapperFactory,
            ResourceMapperFactory<List<PasswordValidationData>,String> listStringResourceMapperFactory) {
        this.passwordValidatorService = passwordValidatorService;
        this.stringListResourceMapperFactory = stringListResourceMapperFactory;
        this.listStringResourceMapperFactory = listStringResourceMapperFactory;
    }

    @Override
    public String validatePasswords(String passwordValidationData) {
        return listStringResourceMapperFactory.createModel(passwordValidatorService.validatePasswords(
                stringListResourceMapperFactory.createModel(passwordValidationData)));
    }



}
