package com.example.test.validation;

import android.widget.EditText;

public class SingUpValidation {
    public boolean validateFields(EditText fullName, EditText phone,
                                  EditText email, EditText pass, EditText passConf) {
        String fullNameValue = fullName.getText().toString().trim();
        String phoneValue = phone.getText().toString().trim();
        String emailValue = email.getText().toString().trim();
        String passValue = pass.getText().toString();
        String passConfValue = passConf.getText().toString();

        if (!isFullNameValid(fullNameValue)) {
            fullName.setError("Введите полное имя (не менее 3 символов)");
            return false;
        }

        if (!isPhoneValid(phoneValue)) {
            phone.setError("Введите корректный номер телефона");
            return false;
        }

        if (!isEmailValid(emailValue)) {
            email.setError("Введите корректный адрес электронной почты");
            return false;
        }

        if (!isPasswordValid(passValue)) {
            pass.setError("Пароль должен быть не менее 8 символов и содержать букву, цифру и спецсимвол");
            return false;
        }

        if (!isPasswordConfirmed(passValue, passConfValue)) {
            passConf.setError("Пароли не совпадают");
            return false;
        }

        return true;
    }

    private boolean isFullNameValid(String fullName) {
        return fullName != null && fullName.trim().length() > 2;
    }

    private boolean isPhoneValid(String phone) {
        return phone != null && phone.matches("\\+\\d{11,}");
    }

    private boolean isEmailValid(String email) {
        return email != null && email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    private boolean isPasswordConfirmed(String password, String confirmPassword) {
        return password != null && password.equals(confirmPassword);
    }
}
