package com.example.financeappbackend.domain.transfer;

public record TransferDTO(
        String SenderWallet,
        String ReceiverWallet,
        Long valueInCents,
        String description,
        String id
) {
}
