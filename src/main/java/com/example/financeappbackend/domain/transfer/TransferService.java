package com.example.financeappbackend.domain.transfer;

import com.example.financeappbackend.domain.wallet.Wallet;
import com.example.financeappbackend.domain.wallet.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferService {

    final
    TransferRepository transferRepository;

    final
    WalletRepository walletRepository;

    public TransferService(WalletRepository walletRepository, TransferRepository transferRepository) {
        this.walletRepository = walletRepository;
        this.transferRepository = transferRepository;
    }

    public TransferDTOResponse createTransfer(TransferDTORequest transfer){

        Optional<Wallet> senderWallet = walletRepository.findById(transfer.senderWallet());
        Optional<Wallet> receiverWallet = walletRepository.findById(transfer.receiverWallet());

        if(senderWallet.isEmpty() || receiverWallet.isEmpty()){
            return null;
        }

        Wallet senderWalletObj = senderWallet.get();
        Wallet receiverWalletObj = receiverWallet.get();

        if(senderWalletObj.getAmount_in_cents() >= transfer.valueInCents()){
            senderWalletObj.setAmount_in_cents(senderWalletObj.getAmount_in_cents() - transfer.valueInCents());
            receiverWalletObj.setAmount_in_cents(receiverWalletObj.getAmount_in_cents() + transfer.valueInCents());
            walletRepository.save(senderWalletObj);
            walletRepository.save(receiverWalletObj);
        }

        Transfer newTransfer = new Transfer(
                transfer.description(),
                transfer.valueInCents(),
                senderWalletObj,
                receiverWalletObj);

        transferRepository.save(newTransfer);

        return new TransferDTOResponse(
                newTransfer.getSender_wallet_id(),
                newTransfer.getReceiver_wallet_id(),
                newTransfer.value_in_cents,
                newTransfer.getDescription(),
                newTransfer.getId()
        );
    }
}
