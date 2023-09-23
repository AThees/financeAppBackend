package com.example.financeappbackend.controllers;

import com.example.financeappbackend.domain.transfer.TransferDTOWithId;
import com.example.financeappbackend.domain.transfer.TransferDTOWithObject;
import com.example.financeappbackend.domain.transfer.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    final
    TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<TransferDTOWithObject> createTransfer(@RequestBody TransferDTOWithId transfer){
        TransferDTOWithObject answer = transferService.createTransfer(transfer);
        if(answer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(answer);
    }

    @GetMapping
    public ResponseEntity<List<TransferDTOWithId>> getAllTransfers(){
        List<TransferDTOWithId> answer = transferService.getAllTransfers();
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferDTOWithId> getTransferById(@PathVariable("id") String id){
        TransferDTOWithId answer = transferService.getTransferById(id);
        if(answer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(answer);
    }


}
