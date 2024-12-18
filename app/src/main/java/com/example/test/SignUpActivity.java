package com.example.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.model.User;
import com.example.test.service.UserService;
import com.example.test.validation.SingUpValidation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        UserService userService = new UserService();
        SingUpValidation validation = new SingUpValidation();

        Button singIn = findViewById(R.id.button_sing_up);
        TextView singUp = findViewById(R.id.sign_in);
        EditText fullName = findViewById(R.id.full_name);
        EditText phone = findViewById(R.id.text_phone);
        EditText email = findViewById(R.id.text_email);
        EditText pass = findViewById(R.id.text_pass);
        EditText passConf = findViewById(R.id.text_pass_conf);


        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, Log_in.class);
                startActivity(intent);
                finish();
            }
        });

        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation.validateFields(fullName, phone, email, pass, passConf)) {
                    userService.createUser(User.builder()
                            .fullName(fullName.getText().toString())
                            .phone(phone.getText().toString())
                            .email(email.getText().toString())
                            .password(pass.getText().toString())
                            .build(), new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()) {
                                User createdUser = response.body();
                                System.out.println("Созданный пользователь: "
                                        + createdUser.getFullName());
                            } else {
                                System.out.println("Ошибка: " + response.code() + " "
                                        + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            System.out.println("Ошибка подключения: " + t.getMessage());
                        }
                    });
                    saveUserRegistrationStatus();

                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void saveUserRegistrationStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isRegistered", true);
        editor.apply();
    }
}