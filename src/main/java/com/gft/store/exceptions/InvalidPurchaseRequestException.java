package com.gft.store.exceptions;

public class InvalidPurchaseRequestException extends RuntimeException {

    public InvalidPurchaseRequestException(String msg) {
        super(msg);
    }

}
