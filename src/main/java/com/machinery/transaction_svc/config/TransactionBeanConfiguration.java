package com.machinery.transaction_svc.config;

import com.machinery.transaction_svc.validator.TransactionValidation;
import com.machinery.transaction_svc.validator.impl.EmptyTransactionValidationBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionBeanConfiguration {

    @Bean
    @ConditionalOnMissingBean // Dessa forma, caso o bean estiver ausente, retornar um bean vazio.
    @Conditional(TransactionEnableNewerThanJavaSeventeen.class)
    public TransactionValidation emptyTransactionNewerThanJavaSeventeenValidation() {
        return new EmptyTransactionValidationBean();
    }

    @ConditionalOnJava(range = ConditionalOnJava.Range.OLDER_THAN, value = JavaVersion.SEVENTEEN)
    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNewerThanJavaSeventeen.class)
    public TransactionValidation emptyTransactionJavaOlderThanSeventeenValidation() {
        return new EmptyTransactionValidationBean();
    }
}
