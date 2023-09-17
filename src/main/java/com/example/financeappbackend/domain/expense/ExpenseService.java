package com.example.financeappbackend.domain.expense;

import com.example.financeappbackend.domain.wallet.Wallet;
import com.example.financeappbackend.domain.wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        List<ExpenseDTO> answer = new ArrayList<>();

        for(Expense expense : expenses){
            ExpenseDTO dto = new ExpenseDTO(
                    expense.getDescription(),
                    expense.getValue_in_cents(),
                    expense.getPaid(),
                    expense.getCategory(),
                    expense.getWalletId().getId(),
                    expense.getId()
            );

            answer.add(dto);
        }

            return answer;
    }
}