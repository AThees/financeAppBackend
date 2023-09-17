package com.example.financeappbackend.domain.expense;

import com.example.financeappbackend.domain.wallet.Wallet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="expenses")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Expense {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String description;
    private Long value_in_cents;
    private Boolean paid;
    private String category;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet walletId;

    public Expense(ExpenseDTO data, Wallet wallet){
        this.description = data.description();
        this.category = data.category();
        this.value_in_cents = data.valueInCents();
        this.paid = data.paid();
        this.walletId = wallet;
    }
}
