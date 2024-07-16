package com.machinery.transaction_svc.api;

import com.machinery.transaction_svc.domain.TransactionBusiness;
import com.machinery.transaction_svc.dto.TransactionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransferController {

    public final TransactionBusiness transactionBusiness;

    public TransferController(TransactionBusiness transactionBusiness) {
        this.transactionBusiness = transactionBusiness;
    }

    @GetMapping(value = "/{agencia}/{conta}")
    public List<TransactionDto> buscarTransferencias(@PathVariable("agencia") final Long agencia, @PathVariable("conta") final Long conta) {

        return transactionBusiness.findByConta(agencia, conta);
    }
}

