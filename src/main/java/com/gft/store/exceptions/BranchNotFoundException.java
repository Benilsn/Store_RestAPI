package com.gft.store.exceptions;

public class BranchNotFoundException extends RuntimeException {

    public BranchNotFoundException(String msg) {
        super(msg);
    }

}
