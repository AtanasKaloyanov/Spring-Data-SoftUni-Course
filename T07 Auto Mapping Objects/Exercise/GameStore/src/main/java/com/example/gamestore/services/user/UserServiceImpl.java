package com.example.gamestore.services.user;

import com.example.gamestore.domain.dtos.UserLogin;
import com.example.gamestore.domain.dtos.UserRegister;
import com.example.gamestore.domain.entities.User;
import com.example.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.gamestore.constant.Validations.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private User user;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(String[] arguments) throws IllegalAccessException {
        final String email = arguments[1];
        final String password = arguments[2];
        final String confirPassword = arguments[3];
        final String fullName = arguments[4];

        UserRegister userRegisterDTO;

        try {
            userRegisterDTO = new UserRegister(email, password, confirPassword, fullName);
        } catch (IllegalArgumentException exception) {
            return exception.getMessage();
        }

        final User user = this.modelMapper.map(userRegisterDTO, User.class);
        // final  User sameUser = userRegister.toUser();

        if (this.userRepository.count() == 0) {
            user.setAdmine(true);
        }

        boolean doesUserExists = this.userRepository.findFirstByEmail(userRegisterDTO.getEmail()).isPresent();

        if (doesUserExists) {
            //  throw new IllegalArgumentException(SAME_EMAIL_MESSAGE);
            return SAME_EMAIL_MESSAGE;
        }

        this.userRepository.save(user);

        return userRegisterDTO.successfulRegisterFormat();

    }

    @Override
    public String login(String[] args) {
        final String email = args[1];
        final String password = args[2];

        UserLogin userLogin = new UserLogin(email, password);

        Optional<User> user = this.userRepository.findFirstByEmail(userLogin.getEmail());

        if (this.user != null) {
            return ALREADY_LOGIN_MESSAGE;
        }

        if (user.isPresent()
                && user.get().getPassword().equals(userLogin.getPassword())) {
            this.user = this.userRepository.findFirstByEmail(userLogin.getEmail()).get();

            return String.format(SUCCESSFUL_LOGIN_MESSAGE, this.user.getFullName());
        }

        return USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE;
    }

    @Override
    public String logout() {
        if (this.user == null) {
            return CAN_NOT_LOG_OUT_MESSAGE;
        }

        String output = String.format(LOGOUT_MESSAGE, this.user.getFullName());

        this.user = null;

        return output;
    }
}
