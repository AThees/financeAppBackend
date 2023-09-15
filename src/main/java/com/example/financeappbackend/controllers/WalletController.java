package com.example.financeappbackend.controllers;

import com.example.financeappbackend.domain.wallet.WalletDTO;
import com.example.financeappbackend.domain.wallet.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/wallet")
public class WalletController {

    final
    WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WalletDTO> addWallet(@RequestBody WalletDTO wallet){
        WalletDTO newWallet = service.createNewWallet(wallet);
        return ResponseEntity.ok(newWallet);
    }

    @GetMapping
    public ResponseEntity<List<WalletDTO>> getAllWallets(){
        var allWallets = service.getAllWallets();
        return ResponseEntity.ok(allWallets);
    }

    @GetMapping("/{id}")
    public ResponseEntity getWalletById(@PathVariable("id") String id){
        WalletDTO wallet = service.getWalletById(id);
        if(wallet == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(wallet);
        }
    }

}
