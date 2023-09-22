package com.example.financeappbackend.domain.transfer;

public record TransferDTORequest(
        String senderWallet,
        String receiverWallet,
        Long valueInCents,
        String description,
        String id
) {
}
