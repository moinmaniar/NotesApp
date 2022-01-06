package com.example.customdialog.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.customdialog.HomePage;
import com.example.customdialog.R;
import com.example.customdialog.databinding.ActivityLoginPageBinding;

public class LoginPage extends AppCompatActivity {
    private ActivityLoginPageBinding binding;
    EditText username, password;
    Button btnLogin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login_page);

        username = (EditText) findViewById(R.id.login_email);
        password =  findViewById(R.id.login_pass);
        btnLogin = findViewById(R.id.login_button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")){
                    Toast.makeText(LoginPage.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = DB.checkUserNameAndPassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginPage.this,"Login successfull!",Toast.LENGTH_SHORT).show();
                        Intent intent   = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginPage.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}