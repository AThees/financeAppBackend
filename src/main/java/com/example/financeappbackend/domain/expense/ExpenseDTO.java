package com.example.financeappbackend.domain.expense;

public record ExpenseDTO(
        String description,
        Long valueInCents,
        Boolean paid,
        String category,
        String walletId,
        String id
) {}
