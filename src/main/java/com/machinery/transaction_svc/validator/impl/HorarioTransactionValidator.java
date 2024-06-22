package com.machinery.transaction_svc.validator.impl;

import com.machinery.transaction_svc.dto.RequestTransactionDto;
import com.machinery.transaction_svc.exception.DomainBusinessException;
import com.machinery.transaction_svc.validator.TransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty( // Condicional para permitir injetar o bean?
        value = "transaction.validation.horario",
        havingValue = "true",
        matchIfMissing = false)
public class HorarioTransactionValidator implements TransactionValidator {

    public static final int HOUR_END = 18;

    @Override
    public void validate(RequestTransactionDto request) throws DomainBusinessException {

        if (LocalDateTime.now().getHour() > HOUR_END || request.getData().getHour() > HOUR_END) {
            throw new DomainBusinessException("Falha para transacionar os valores após último horário. [%dh00]".formatted(HOUR_END));
        }
    }
}
