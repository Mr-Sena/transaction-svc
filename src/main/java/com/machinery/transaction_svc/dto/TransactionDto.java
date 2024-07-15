package com.machinery.transaction_svc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
@ToString(of = {"uuid", "situacao"})
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

    public void suspeitaFraude() {
        situacao = SituacaoEnum.EM_SUSPEITA_FRAUDE;
    }

    public void analisada() {
        situacao = SituacaoEnum.ANALISADA;
    }

    public void emAnaliseHumana() {
        situacao = SituacaoEnum.EM_ANALISE_HUMANA;
    }

    @JsonIgnore
    public boolean isAnalisada() {
        return this.situacao.equals(SituacaoEnum.ANALISADA);
    }

    public void aprovarTransferencia() {

        this.situacao = SituacaoEnum.APROVADA;
    }
}
