package com.example.financeappbackend.domain.wallet;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WalletService {

    final
    WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
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
                Collections.emptyList()
        );
        return answer;
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
                    Collections.emptyList()
            );

            answer.add(dto);
        }

         return answer;
    }

}
