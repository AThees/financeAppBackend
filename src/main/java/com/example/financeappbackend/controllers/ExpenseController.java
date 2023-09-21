package com.example.financeappbackend.controllers;

import com.example.financeappbackend.domain.expense.ExpenseDTO;
import com.example.financeappbackend.domain.expense.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    final
    ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expense){
        ExpenseDTO newExpense = expenseService.createExpense(expense);
        return ResponseEntity.ok(newExpense);
    }

    @GetMapping()
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses(){
        List<ExpenseDTO> answer = expenseService.getAllExpenses();
        return ResponseEntity.ok(answer);
    }
}