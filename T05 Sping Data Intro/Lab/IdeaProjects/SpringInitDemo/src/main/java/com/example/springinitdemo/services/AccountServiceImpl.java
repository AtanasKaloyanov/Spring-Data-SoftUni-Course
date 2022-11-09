package com.example.springinitdemo.services;

import com.example.springinitdemo.models.Account;
import com.example.springinitdemo.repositories.AccounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private AccounterRepository accounterRepository;

    @Autowired
    public AccountServiceImpl(AccounterRepository accounterRepository) {
        this.accounterRepository = accounterRepository;
    }


    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {
        Optional<Account> account = this.accounterRepository.findById(id);

        if (account.isEmpty()) {
            throw new RuntimeException("Account does not exists");
        }


        BigDecimal current = account.get().getBalance();

        if (amount.compareTo(current) > 0) {
            throw new RuntimeException("Cannot withdraw!");
        }

        account.get().setBalance(current.subtract(amount));

        this.accounterRepository.save(account.get());
    }

    @Override
    public void depositMoney(BigDecimal amount, Long id) {
        Account account = this.accounterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No account"));

        BigDecimal balance = account.getBalance();
        BigDecimal add = balance.add(amount);
        account.setBalance(add);

        this.accounterRepository.save(account);
    }
}
