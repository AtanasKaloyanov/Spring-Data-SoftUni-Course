package com.example.springinitdemo;

import com.example.springinitdemo.models.User;
import com.example.springinitdemo.services.AccountService;
import com.example.springinitdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;

    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
       // this.userService.register("Valcho", 1000);

        User user = this.userService.findByUsername("Valcho");
        this.accountService.depositMoney(BigDecimal.TEN, user.getAccounts().get(0).getId());
        this.accountService.withdrawMoney(BigDecimal.ONE, user.getAccounts().get(0).getId());
    }
}
