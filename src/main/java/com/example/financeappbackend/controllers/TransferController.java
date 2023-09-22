package com.example.financeappbackend.controllers;

import com.example.financeappbackend.domain.transfer.TransferService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransferController {

    TransferService transactionService;



}
