package com.machinery.transaction_svc.validator;

import com.machinery.transaction_svc.dto.RequestTransactionDto;
import com.machinery.transaction_svc.exception.DomainBusinessException;
import com.machinery.transaction_svc.validator.impl.BancoTransactionValidator;
import com.machinery.transaction_svc.validator.impl.HorarioTransactionValidator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnBean(value = {BancoTransactionValidator.class, HorarioTransactionValidator.class}) //Esse beans precisar estar ativos(?)
@ConditionalOnExpression("${transaction.validation.enabled:true}") // O valor do atributo global (enable) precisa ser true?
@Slf4j
public class TransactionValidationBean implements TransactionValidation {

    //Auto config
    private List<TransactionValidator> transactionValidatorList = new ArrayList<>();

    @PostConstruct
    public void addBeans() { // Adicionar todas os beans necessário para efetuar a validação.
        transactionValidatorList.add(new BancoTransactionValidator());
        transactionValidatorList.add(new HorarioTransactionValidator());
    }

    @Override
    public void validate(RequestTransactionDto requestData) throws DomainBusinessException {
        transactionValidatorList.stream().forEach(transactionValidator -> transactionValidator.validate(requestData));
    }
}
