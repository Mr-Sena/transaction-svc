package com.machinery.transaction_svc.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class BeneficiarioDto implements Serializable {

    private static final long serialVersionID = 2806421543985360625L;

    @NotNull(message = "Dados do CPF.")
    private Long CPF;

    @NotNull(message = "Campo para informar o código de banco de destino.")
    private Long codigoBanco;

    @NotNull( message = "Campo para informar a agência do destino." )
    private String agencia;

    @NotNull( message = "Campo para informar os dígitos da conta do destino." )
    private String conta;

    @NotNull( message = "Campo para informar o nome do favorecido do pagamento." )
    private String nomeFavorecido;

}
