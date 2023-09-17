package com.example.financeappbackend.domain.expense;

import com.example.financeappbackend.domain.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, String> {
    List<Expense> findByWalletId(Wallet walletId);
}
