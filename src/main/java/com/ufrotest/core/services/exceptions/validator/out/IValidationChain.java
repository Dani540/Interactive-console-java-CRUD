package com.ufrotest.core.services.exceptions.validator.out;

public interface IValidationChain<E> {
    void validate(E entity) throws IValidateException;
}
