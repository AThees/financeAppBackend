package com.example.financeappbackend.domain.transfer;

public record TransferDTOWithId(
        String senderWallet,
        String receiverWallet,
        Long valueInCents,
        String description,
        String id
) {
}
