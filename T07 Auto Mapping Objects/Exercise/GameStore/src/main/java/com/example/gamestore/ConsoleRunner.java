package com.example.gamestore;

import com.example.gamestore.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.example.gamestore.constant.Commands.*;
import static com.example.gamestore.constant.Validations.WRONG_COMMAND_MESSAGE;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private static final Scanner scanner = new Scanner(System.in);
    private  final UserService userService ;

    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        String input = scanner.nextLine();

        while (!input.equals(LAST_COMMAND)) {

            final String[] arguments = input.split("\\|");
            final String command = arguments[0];

            final String output = switch (command) {
                case REGISTER_USER -> userService.registerUser(arguments);

                case LOGIN_USER -> userService.login(arguments);

                case LOGOUT_COMMAND -> userService.logout();

                default -> WRONG_COMMAND_MESSAGE;
            };

            System.out.println(output);
            input = scanner.nextLine();
        }
    }
}
