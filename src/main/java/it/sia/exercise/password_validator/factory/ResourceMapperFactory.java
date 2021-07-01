package it.sia.exercise.password_validator.factory;

public interface ResourceMapperFactory<T, U> {

    U createModel(T dto);

}

