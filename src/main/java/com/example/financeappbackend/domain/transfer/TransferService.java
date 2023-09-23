package com.example.financeappbackend.domain.transfer;

import com.example.financeappbackend.domain.wallet.Wallet;
import com.example.financeappbackend.domain.wallet.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public TransferDTOWithObject createTransfer(TransferDTOWithId transfer){

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

        return new TransferDTOWithObject(
                newTransfer.getSender_wallet_id(),
                newTransfer.getReceiver_wallet_id(),
                newTransfer.value_in_cents,
                newTransfer.getDescription(),
                newTransfer.getId()
        );
    }

    public List<TransferDTOWithId> getAllTransfers() {
        List<Transfer> allTransfers = transferRepository.findAll();

        List<TransferDTOWithId> dtos = new ArrayList<>();

        for(Transfer transfer: allTransfers){
            TransferDTOWithId dto = new TransferDTOWithId(
                    transfer.getSender_wallet_id().getId(),
                    transfer.getReceiver_wallet_id().getId(),
                    transfer.getValue_in_cents(),
                    transfer.getDescription(),
                    transfer.getId()
            );

            dtos.add(dto);
        }

        return dtos;

    }

    public TransferDTOWithId getTransferById(String id) {
        Optional<Transfer> transfer = transferRepository.findById(id);
        if(transfer.isEmpty()){
            return null;
        }
        Transfer transferObj = transfer.get();

        return new TransferDTOWithId(
                transfer.get().getSender_wallet_id().getId(),
                transfer.get().getReceiver_wallet_id().getId(),
                transferObj.getValue_in_cents(),
                transferObj.getDescription(),
                transferObj.getId()
        );
    }
}
