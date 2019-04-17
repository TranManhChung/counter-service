package com.wallet.counter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "balances")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "balance_value")
    private Long balanceValue;

}
