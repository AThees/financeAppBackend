package com.example.financeappbackend.domain.wallet;

import com.example.financeappbackend.domain.expense.ExpenseDTO;

import java.util.List;

public record WalletDTO(String name,
                        Long amountInCents,
                        String financeInstitution,
                        String type,
                        Boolean addToSum,
                        List<ExpenseDTO> expenses,
                        String id) {}
