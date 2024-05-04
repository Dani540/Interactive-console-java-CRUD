package com.ufrotest.core.services.exceptions.validator.out;

public interface IValidator<DTO> {
    boolean validate(DTO DTO) throws IValidateException;
}
