package com.example.financeappbackend.domain.expense;

import com.example.financeappbackend.domain.wallet.Wallet;
import com.example.financeappbackend.domain.wallet.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    final
    ExpenseRepository expenseRepository;

    final
    WalletRepository walletRepository;

    public ExpenseService(ExpenseRepository expenseRepository, WalletRepository walletRepository) {
        this.expenseRepository = expenseRepository;
        this.walletRepository = walletRepository;
    }

    public List<Expense> findByWalletId(String id){
        Optional<Wallet> wallet = walletRepository.findById(id);

        if(wallet.isEmpty()){
            return null;
        }

        Wallet walletObj = wallet.get();

        return expenseRepository.findByWalletId(walletObj);
    }

    public List<ExpenseDTO> createDtoArray(List<Expense> expenses){

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

        return expenseDTOS;
    }

    public ExpenseDTO createExpense(ExpenseDTO expense) {
        Optional<Wallet> wallet = walletRepository.findById(expense.walletId());
        if(wallet.isEmpty()){
            return null;
        }
        Wallet walletObj = wallet.get();
        Expense newExpense = new Expense(expense, walletObj);
        expenseRepository.save(newExpense);
        return new ExpenseDTO(
                newExpense.getDescription(),
                newExpense.getValue_in_cents(),
                newExpense.getPaid(),
                newExpense.getCategory(),
                newExpense.getWalletId().getId(),
                newExpense.getId()
        );
    }

    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();

        return createDtoArray(expenses);

    }

    public List<ExpenseDTO> getExpenseFromWallet(String id) {
        var answer = findByWalletId(id);

        if (answer == null){
            return null;
        }

        List<Expense> expenses = findByWalletId(id);

        return createDtoArray(expenses);

    }

    public ExpenseDTO updateExpense(String id, ExpenseDTO changes) {

        Optional<Expense> expenseToUpdate = expenseRepository.findById(id);

        if(expenseToUpdate.isEmpty()){
            return null;
        }

        Expense expense = expenseToUpdate.get();

        if(changes.description() != null) {
            expense.setDescription(changes.description());
        }

        if(changes.valueInCents() != null){
            expense.setValue_in_cents(changes.valueInCents());
        }

        if(changes.paid() != null){
            expense.setPaid(changes.paid());
        }

        if (changes.category() != null) {
            expense.setCategory(changes.category());
        }

        if(changes.walletId() != null){
            Optional<Wallet> wallet = walletRepository.findById(changes.walletId());

            if(wallet.isEmpty()){
                throw new RuntimeException("Carteira não encontrada!");
            }

            expense.setWalletId(wallet.get());
        }

        expenseRepository.save(expense);

        return new ExpenseDTO(
                expense.getDescription(),
                expense.getValue_in_cents(),
                expense.getPaid(),
                expense.getCategory(),
                expense.getWalletId().getId(),
                expense.getId()
        );
    }
}
