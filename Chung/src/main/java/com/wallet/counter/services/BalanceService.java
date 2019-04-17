package com.wallet.counter.services;

import com.wallet.counter.repositories.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;


}
