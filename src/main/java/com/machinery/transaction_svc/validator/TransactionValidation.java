package com.machinery.transaction_svc.validator;

import com.machinery.transaction_svc.dto.RequestTransactionDto;
import com.machinery.transaction_svc.exception.DomainBusinessException;

public interface TransactionValidation {

    public void validate(final RequestTransactionDto requestData) throws DomainBusinessException;
}
