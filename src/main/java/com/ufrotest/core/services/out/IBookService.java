package com.ufrotest.core.services.out;

import com.ufrotest.core.model.IEntityDTO;
import com.ufrotest.core.repositories.RepositoryABS;
import com.ufrotest.core.services.exceptions.validator.out.IValidationChain;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class IBookService<DTO extends IEntityDTO> {
    private final RepositoryABS<DTO> repository;
    private final IValidationChain<DTO> validationChain;
}
