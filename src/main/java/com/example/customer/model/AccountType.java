package com.example.customer.model;

public enum AccountType {
    S("Savings"),
    C("Checking");

    public final String label;

    private AccountType(String label) {
        this.label = label;
    }
}
