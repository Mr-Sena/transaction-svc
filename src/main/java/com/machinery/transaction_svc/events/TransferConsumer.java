package com.machinery.transaction_svc.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machinery.transaction_svc.domain.TransactionBusiness;
import com.machinery.transaction_svc.dto.SituacaoEnum;
import com.machinery.transaction_svc.dto.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class TransferConsumer {

    private final ObjectMapper objectMapper;
    private final TransactionBusiness transactionBusiness;

    public TransferConsumer(ObjectMapper objectMapper, TransactionBusiness transactionBusiness) {
        this.objectMapper = objectMapper;
        this.transactionBusiness = transactionBusiness;
    }

    @KafkaListener(topics = "${events.consumeTopic}")
    public void consumirTransferencia(String message) {

        try {
            var transfer = getTransaction(message);
            transactionBusiness.atualizarTransferencia(transfer);
            //todo: Processamento da transferência.
        } catch (IOException problem) {
            log.error("Falha de processamento. {}", problem.getMessage());
        }
    }

    @KafkaListener(topics = "${events.returnTopic}")
    public void consumirTransferenciaRetorno(String message) {

        try {
            var transfer = getTransaction(message);
            log.info("Transferência recebida de análise. {}", transfer);
            if (!transfer.isAnalisada()) {
                return;
            } else {
                log.info("Transferência analisada: {}", transfer);
                transactionBusiness.aprovarTransferencia(transfer);
            }
        } catch (IOException problem) {
            log.error("Falha de processamento. {}", problem.getMessage());
        }
    }

    private TransactionDto getTransaction(String message) throws IOException {

        var transactionDto = objectMapper.readValue(message, TransactionDto.class);
        if (Objects.isNull(transactionDto.getSituacao())) {
            transactionDto.setSituacao(SituacaoEnum.NAO_ANALISADA);
        }
        transactionDto.setData(LocalDateTime.now());
        return transactionDto;
    }
}
