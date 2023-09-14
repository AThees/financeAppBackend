package com.example.financeappbackend.domain.wallet;

public record WalletDTO(String name, Integer amountInCents, String financeInstitution, String type, Boolean addToSum) {
}
