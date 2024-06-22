package com.machinery.transaction_svc.domain;

import com.machinery.transaction_svc.dto.RequestTransactionDto;
import com.machinery.transaction_svc.exception.DomainBusinessException;
import com.machinery.transaction_svc.repositories.TransactionRepository;
import com.machinery.transaction_svc.validator.TransactionValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TransactionBusiness {

    private TransactionRepository repository;
    private TransactionValidation transactionValidation;


    public TransactionBusiness(TransactionRepository repository, TransactionValidation transactionValidation) {
        this.repository = repository;
        this.transactionValidation = transactionValidation;
    }


    public void save(final RequestTransactionDto entity) {

        if (Objects.isNull(entity.getData()))
            entity.setData(LocalDateTime.now());
        transactionValidation.validate(entity);
        repository.save(entity);
    }
}
