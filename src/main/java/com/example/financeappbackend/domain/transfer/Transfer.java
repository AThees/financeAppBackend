package com.example.financeappbackend.domain.transfer;

import com.example.financeappbackend.domain.wallet.Wallet;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="transactions")
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


}
