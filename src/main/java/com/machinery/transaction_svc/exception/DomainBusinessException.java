package com.machinery.transaction_svc.exception;

public class DomainBusinessException extends RuntimeException {

    public DomainBusinessException(String failureMessage) {
        super(failureMessage);
    }
}
