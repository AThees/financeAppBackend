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

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses(){
        List<ExpenseDTO> answer = expenseService.getAllExpenses();
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/byWallet/{id}")
    public ResponseEntity<List<ExpenseDTO>> getExpensesFromWallet(@PathVariable("id") String id){
        List<ExpenseDTO> answer = expenseService.getExpenseFromWallet(id);

        if(answer == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(answer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable("id") String id, @RequestBody ExpenseDTO changes){
        ExpenseDTO answer = expenseService.updateExpense(id, changes);

        if(answer == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntity> deleteExpense(@PathVariable("id") String id){
        Boolean deleted = expenseService.deleteExpense(id);
        if(!deleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
