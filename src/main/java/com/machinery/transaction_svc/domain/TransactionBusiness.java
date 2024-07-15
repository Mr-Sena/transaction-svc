package com.machinery.transaction_svc.domain;

import com.machinery.transaction_svc.dto.RequestTransactionDto;
import com.machinery.transaction_svc.dto.TransactionDto;
import com.machinery.transaction_svc.repositories.TransactionRepository;
import com.machinery.transaction_svc.validator.TransactionValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
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


    public void atualizarTransferencia(TransactionDto transfer) {
        log.info("Atualizar transferência: {}", transfer);
        repository.save(transfer);
    }


    public void aprovarTransferencia(TransactionDto transferEvents) {

        var transferencia = buscarTransferencia(transferEvents);
        if (transferencia.isPresent()) {
            var dataTransfer  = transferencia.get();
            if (transferEvents.isAnalisada() && !dataTransfer.isAnalisada()) { //Últimos dados de transferência atualizado analisado, e o primeiro cadastro do registro
                dataTransfer.aprovarTransferencia();
                atualizarTransferencia(dataTransfer);
                log.info("Transferência aprovada. {}", dataTransfer);
            }
        }
    }

    public Optional<TransactionDto> buscarTransferencia(TransactionDto transferencia) {
        return repository.findById(transferencia.getUuid());
    }
}
