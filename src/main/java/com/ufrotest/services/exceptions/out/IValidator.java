package com.ufrotest.services.exceptions.out;

public interface IValidator<DTO> {
    boolean validate(DTO DTO) throws IValidateException;
}
