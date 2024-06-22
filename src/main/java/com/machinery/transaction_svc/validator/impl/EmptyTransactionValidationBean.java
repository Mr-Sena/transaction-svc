package com.machinery.transaction_svc.validator.impl;

import com.machinery.transaction_svc.dto.RequestTransactionDto;
import com.machinery.transaction_svc.exception.DomainBusinessException;
import com.machinery.transaction_svc.validator.TransactionValidation;

public class EmptyTransactionValidationBean implements TransactionValidation {

    @Override
    public void validate(RequestTransactionDto requestData) throws DomainBusinessException {

    }
}
