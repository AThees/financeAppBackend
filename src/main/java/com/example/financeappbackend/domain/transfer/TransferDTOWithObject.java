package com.example.financeappbackend.domain.transfer;

import com.example.financeappbackend.domain.wallet.Wallet;

public record TransferDTOWithObject(
        Wallet SenderWallet,
        Wallet ReceiverWallet,
        Long valueInCents,
        String description,
        String id
){}
