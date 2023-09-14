package com.example.financeappbackend.domain.wallet;

import com.example.financeappbackend.dtos.WalletDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="wallets")
@Table(name="wallets")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Wallet {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private Integer amount_in_cents;
    private String finance_institution;
    private String type;
    private Boolean add_to_sum;

    public Wallet(WalletDTO data){
        this.name = data.name();
        this.amount_in_cents = data.amountInCents();
        this.finance_institution = data.financeInstitution();
        this.type = data.type();
        this.add_to_sum = data.addToSum();
    }
}
