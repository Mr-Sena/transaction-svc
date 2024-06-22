package com.machinery.transaction_svc.validator.impl;

import com.machinery.transaction_svc.dto.RequestTransactionDto;
import com.machinery.transaction_svc.exception.DomainBusinessException;
import com.machinery.transaction_svc.validator.TransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component //Auto-detected spring bean.
@ConditionalOnProperty( // Condicional para permitir injetar o bean?
        value = "transaction.validation.banco",
        havingValue = "true",
        matchIfMissing = false)
public class BancoTransactionValidator implements TransactionValidator {
    public static final int CODIGO_BANCO = 785;

    @Override
    public void validate(RequestTransactionDto request) throws DomainBusinessException {

        if (Objects.isNull( request.getBeneficiario() )) {
            throw new DomainBusinessException("Banco de beneficiário inválido.");
        } else if (Objects.isNull( request.getBeneficiario().getCodigoBanco() ) ||
        request.getBeneficiario().getCodigoBanco().compareTo( (long) CODIGO_BANCO ) != 0) {
            throw new DomainBusinessException("Banco de beneficiário inválido.");
        }
    }
}
