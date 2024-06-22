package com.machinery.transaction_svc.validator;

import com.machinery.transaction_svc.dto.RequestTransactionDto;
import com.machinery.transaction_svc.exception.DomainBusinessException;

@FunctionalInterface
public interface TransactionValidator {

    void validate(final RequestTransactionDto request) throws DomainBusinessException;
}
