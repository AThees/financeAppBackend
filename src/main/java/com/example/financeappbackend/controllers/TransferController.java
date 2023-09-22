package com.example.financeappbackend.controllers;

import com.example.financeappbackend.domain.transfer.TransferDTORequest;
import com.example.financeappbackend.domain.transfer.TransferDTOResponse;
import com.example.financeappbackend.domain.transfer.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    final
    TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping()
    public ResponseEntity<TransferDTOResponse> createTransfer(@RequestBody TransferDTORequest transfer){
        TransferDTOResponse answer = transferService.createTransfer(transfer);
        return ResponseEntity.ok(answer);
    }

}
