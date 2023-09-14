package com.example.financeappbackend.controllers;

import com.example.financeappbackend.domain.wallet.Wallet;
import com.example.financeappbackend.domain.wallet.WalletRepository;
import com.example.financeappbackend.dtos.WalletDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/wallet")
public class WalletController {

    final
    WalletRepository repository;

    public WalletController(WalletRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity addWallet(@RequestBody WalletDTO wallet){
        Wallet newWallet = new Wallet(wallet);
        System.out.println(newWallet.toString());
        repository.save(newWallet);
        return getAllWallets();
    }

    @GetMapping
    public ResponseEntity getAllWallets(){
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }

}
