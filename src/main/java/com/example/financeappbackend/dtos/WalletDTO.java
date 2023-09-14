package com.example.financeappbackend.dtos;

public record WalletDTO(String name, Integer amountInCents, String financeInstitution, String type, Boolean addToSum) {
}
