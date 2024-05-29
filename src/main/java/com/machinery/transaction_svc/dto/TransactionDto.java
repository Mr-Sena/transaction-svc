package com.machinery.transaction_svc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
public class TransactionDto {

    @Id
    private UUID uuid;

    @NotNull( message = "Campo para informar o valor do pagamento." )
    private BigDecimal value;

    @JsonFormat( pattern = "yyyy-MM-dd'T'HH:mm:ss" )
    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME )
    private LocalDateTime data;

    @NotNull ( message = "Campo para informar a conta de origem do pagamento." )
    @Valid
    private Conta conta;

    @Valid
    private BeneficiarioDto beneficiario;

    @NotNull( message = "Campo para informar o tipo de transação." )
    private TipoTransacao tipoTransacao;

    private SituacaoEnum situacao;
}
