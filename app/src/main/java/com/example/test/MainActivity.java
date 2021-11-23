package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String REMEMBER = "remember";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private CheckBox checkBoxRemember;
    private TextView textViewSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FirebaseDatabase.getInstance().getReference("test").setValue("aaa")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(MainActivity.this, "Good", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(MainActivity.this, "Bad", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword =  (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        checkBoxRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
        textViewSignup = (TextView) findViewById(R.id.signup);
        textViewSignup.setOnClickListener(this);
        preferences = getSharedPreferences("test", MODE_PRIVATE);
        editor = preferences.edit();

//        // put into preferences
//        editor.putString("email", "devin@mail.com");
//        editor.apply();
//        // take our from preferences
//        preferences.getString("email", "default@mail.com");

        checkSharePreferences();
    }

    private void checkSharePreferences() {
        Boolean remember = preferences.getBoolean(REMEMBER, false);
        String email = preferences.getString(EMAIL, "");
        String password = preferences.getString(PASSWORD, "");

        editTextEmail.setText(email);
        editTextPassword.setText(password);
        checkBoxRemember.setChecked(remember);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                logIn();
                break;
            case R.id.signup:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }

    }

    private void logIn() {
        boolean remember = false;
        String email = "", password = "";

        if (checkBoxRemember.isChecked()) {
            remember = true;
            email = editTextEmail.getText().toString();
            password = editTextPassword.getText().toString();
        }

        editor.putBoolean(REMEMBER, remember);
        editor.putString(EMAIL,email);
        editor.putString(PASSWORD, password);
        editor.apply();
    }
}