package com.example.financeappbackend.domain.wallet;

import com.example.financeappbackend.domain.expense.Expense;

import java.util.List;

public record WalletDTO(String name,
                        Long amountInCents,
                        String financeInstitution,
                        String type,
                        Boolean addToSum,
                        List<Expense> expenses,
                        String id) {}
