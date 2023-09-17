package com.example.financeappbackend.domain.wallet;

import com.example.financeappbackend.domain.expense.Expense;
import com.example.financeappbackend.domain.expense.ExpenseDTO;
import com.example.financeappbackend.domain.expense.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    final
    WalletRepository walletRepository;

    final
    ExpenseRepository expenseRepository;

    public WalletService(WalletRepository walletRepository, ExpenseRepository expenseRepository) {
        this.walletRepository = walletRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<WalletDTO> getAllWallets(){
        var allWallets = walletRepository.findAll();

        List<WalletDTO> answer = new ArrayList<>();

        for (Wallet wallet: allWallets) {

            List<Expense> expenses = expenseRepository.findByWalletId(wallet);

            List<ExpenseDTO> expenseDTOS = new ArrayList<>();

            for(Expense expense : expenses){
                ExpenseDTO dto = new ExpenseDTO(
                        expense.getDescription(),
                        expense.getValue_in_cents(),
                        expense.getPaid(),
                        expense.getCategory(),
                        expense.getWalletId().getId(),
                        expense.getId()
                );

                expenseDTOS.add(dto);
            }


            WalletDTO dto = new WalletDTO(
                    wallet.getName(),
                    wallet.getAmount_in_cents(),
                    wallet.getFinance_institution(),
                    wallet.getType(),
                    wallet.getAdd_to_sum(),
                    expenseDTOS,
                    wallet.getId()
            );

            answer.add(dto);
        }

         return answer;
    }

    public WalletDTO getWalletById(String id) {

        Optional<Wallet> wallet = walletRepository.findById(id);

        if (wallet.isEmpty()) {
            return null;
        }

        Wallet walletObj = wallet.get();

        WalletDTO answer;

        List<Expense> expenses = expenseRepository.findByWalletId(walletObj);

        List<ExpenseDTO> expenseDTOS = new ArrayList<>();

        for(Expense expense : expenses){
            ExpenseDTO dto = new ExpenseDTO(
                    expense.getDescription(),
                    expense.getValue_in_cents(),
                    expense.getPaid(),
                    expense.getCategory(),
                    expense.getWalletId().getId(),
                    expense.getId()
            );

            expenseDTOS.add(dto);
        }

        answer = new WalletDTO(walletObj.getName(),
                walletObj.getAmount_in_cents(),
                walletObj.getFinance_institution(),
                walletObj.getType(),
                walletObj.getAdd_to_sum(),
                expenseDTOS,
                walletObj.getId()
        );

        return answer;

    }

    public WalletDTO createNewWallet(WalletDTO wallet){
        Wallet newWallet = new Wallet(wallet);
        walletRepository.save(newWallet);

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
        Optional<Wallet> walletToUpdate = walletRepository.findById(id);
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

        walletRepository.save(walletObj);

        List<Expense> expenses = expenseRepository.findByWalletId(walletObj);

        List<ExpenseDTO> expenseDTOS = new ArrayList<>();

        for(Expense expense : expenses){
            ExpenseDTO dto = new ExpenseDTO(
                    expense.getDescription(),
                    expense.getValue_in_cents(),
                    expense.getPaid(),
                    expense.getCategory(),
                    expense.getWalletId().getId(),
                    expense.getId()
            );

            expenseDTOS.add(dto);
        }

         return new WalletDTO(
                walletObj.getName(),
                walletObj.getAmount_in_cents(),
                walletObj.getFinance_institution(),
                walletObj.getType(),
                walletObj.getAdd_to_sum(),
                 expenseDTOS,
                walletObj.getId()
        );
    }

    public Boolean deleteWallet(String id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(wallet.isEmpty()){
            return false;
        }

        walletRepository.delete(wallet.get());
        return true;
    }

}
