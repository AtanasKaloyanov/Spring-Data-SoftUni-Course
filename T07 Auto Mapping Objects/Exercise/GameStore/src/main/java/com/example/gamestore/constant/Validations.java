package com.example.gamestore.constant;

public enum Validations {
    ;
    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";

    public static final String EMAIL_NOT_VALID_MESSAGE = "Incorrect email.";

    public static final String USERNAME_OR_PASSWORD_NOT_VALID_MESSAGE = "Incorrect username / password";

    public static final String PASSWORD_MISS_MATCH_MESSAGE = "Passwords are not matching";

    public static final String COMMAND_NOT_FOUND_MESSAGE = "Passwords are not matching";

    public static final String WAS_REGISTER = "was register";

    public static final String WRONG_COMMAND_MESSAGE = "No command found";

    public static final String SAME_EMAIL_MESSAGE = "Email arleady exists";
    public static final String SUCCESSFUL_LOGIN_MESSAGE = "Successfully logged in %s";

    public static final String ALREADY_LOGIN_MESSAGE = "You are already login";

    public static final String CAN_NOT_LOG_OUT_MESSAGE = "Cannot log out. No user was logged in.";

    public static final String LOGOUT_MESSAGE = "User %s successfully logged out";


}
