package com.example.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.model.User;
import com.example.test.service.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Log_in extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        UserService userService = new UserService();

        Button singIn = findViewById(R.id.button_sing_in);
        TextView singUp = findViewById(R.id.sign_un);
        TextView forgotPass = findViewById(R.id.forgot_pass);
        EditText email = findViewById(R.id.text_email);
        EditText password = findViewById(R.id.text_pass);

        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userService.existsUserPassEmail(email.getText().toString(),
                        password.getText().toString(), new Callback<List<User>>() {
                            @Override
                            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                                if (response.isSuccessful()) {
                                    List<User> users = response.body();
                                    try {
                                        User user = users.get(0);
                                        System.out.println(user.getFullName() + " зашел в систему");
                                        saveUserRegistrationStatus();

                                        Intent intent = new Intent(Log_in.this,
                                                HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } catch (IndexOutOfBoundsException ex) {
                                        Toast.makeText(Log_in.this,
                                                "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    System.out.println("Ошибка: " + response.code() + " "
                                            + response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<List<User>> call, Throwable t) {
                                System.out.println("Ошибка подключения: " + t.getMessage());
                            }
                        });
            }
        });

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log_in.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log_in.this, Forgot_password.class);
                startActivity(intent);
                finish();
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