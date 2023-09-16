package com.example.financeappbackend.domain.wallet;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    final
    WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    public List<WalletDTO> getAllWallets(){
        var allWallets = repository.findAll();

        List<WalletDTO> answer = new ArrayList<>();

        for (Wallet wallet: allWallets) {
            WalletDTO dto = new WalletDTO(
                    wallet.getName(),
                    wallet.getAmount_in_cents(),
                    wallet.getFinance_institution(),
                    wallet.getType(),
                    wallet.getAdd_to_sum(),
                    Collections.emptyList(),
                    wallet.getId()
            );

            answer.add(dto);
        }

         return answer;
    }

    public WalletDTO getWalletById(String id) {

        Optional<Wallet> wallet = repository.findById(id);

        if (wallet.isEmpty()) {
            return null;
        }

        Wallet walletObj = wallet.get();

        WalletDTO answer;

        answer = new WalletDTO(walletObj.getName(),
                walletObj.getAmount_in_cents(),
                walletObj.getFinance_institution(),
                walletObj.getType(),
                walletObj.getAdd_to_sum(),
                Collections.emptyList(),
                walletObj.getId()
        );

        return answer;

    }

    public WalletDTO createNewWallet(WalletDTO wallet){
        Wallet newWallet = new Wallet(wallet);
        repository.save(newWallet);

        WalletDTO answer;
        answer = new WalletDTO(newWallet.getName(),
                newWallet.getAmount_in_cents(),
                newWallet.getFinance_institution(),
                newWallet.getType(),
                newWallet.getAdd_to_sum(),
                Collections.emptyList(),
                newWallet.getId()
        );
        return answer;
    }
    public WalletDTO updateWallet(WalletDTO changes, String id) {
        Optional<Wallet> walletToUpdate = repository.findById(id);
        if(walletToUpdate.isEmpty()){
            return null;
        }

        Wallet walletObj = walletToUpdate.get();

        if(changes.name() != null){
            walletObj.setName(changes.name());
        }
        if (changes.amountInCents() != null){
            walletObj.setAmount_in_cents(changes.amountInCents());
        }
        if(changes.type() != null){
            walletObj.setType(changes.type());
        }
        if(changes.addToSum() != null){
            walletObj.setAdd_to_sum(changes.addToSum());
        }
        if (changes.financeInstitution() != null){
            walletObj.setFinance_institution(changes.financeInstitution());
        }

        repository.save(walletObj);

         return new WalletDTO(
                walletObj.getName(),
                walletObj.getAmount_in_cents(),
                walletObj.getFinance_institution(),
                walletObj.getType(),
                walletObj.getAdd_to_sum(),
                Collections.emptyList(),
                walletObj.getId()
        );
    }

    public Boolean deleteWallet(String id) {
        Optional<Wallet> wallet = repository.findById(id);
        if(wallet.isEmpty()){
            return false;
        }

        repository.delete(wallet.get());
        return true;
    }

}
