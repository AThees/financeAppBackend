package com.example.financeappbackend.domain.transfer;

import com.example.financeappbackend.domain.wallet.Wallet;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="transfers")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transfer {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String description;
    Long value_in_cents;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sender_wallet_id", nullable = false)
    private Wallet sender_wallet_id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "receiver_wallet_id", nullable = false)
    private Wallet receiver_wallet_id;

    public Transfer(String description, Long value_in_cents, Wallet sender_wallet_id, Wallet receiver_wallet_id){
        this.description = description;
        this.value_in_cents = value_in_cents;
        this.sender_wallet_id = sender_wallet_id;
        this.receiver_wallet_id = receiver_wallet_id;
    }
}
