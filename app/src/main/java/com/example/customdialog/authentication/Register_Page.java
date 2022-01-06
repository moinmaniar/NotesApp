package com.example.customdialog.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customdialog.HomePage;
import com.example.customdialog.R;

import java.util.regex.Pattern;

public class Register_Page extends AppCompatActivity {

   // ActivityRegisterPageBinding binding;
    EditText userName, password, repassword;
    TextView haveAnAccount;
    Button SignUp, SignIn;
    DBHelper DB;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(
            "^" + "(?=.*[@#$%^&+=])" +     // at least 1 special character    ^ -> start of the regex pattern
            "(?=\\S+$)" +            // no white spaces
            ".{6,}" +                // at least 6 characters
            "$");                   // end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityRegisterPageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_register_page);


        userName = findViewById(R.id.text_email);
        password = findViewById(R.id.text_password);
        repassword = findViewById(R.id.text_repassword);

        haveAnAccount = findViewById(R.id.have_an_account);

        SignUp = findViewById(R.id.button_register);
        SignIn = findViewById(R.id.login_button);
        DB = new DBHelper(this);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                Boolean a = validatePassword(pass);
                if(!a)
                {
                    password.setError("password should have atleast 1 character");
                    repassword.setError("password should have atleast 1 character");

                }

                String repass = repassword.getText().toString();



                if (user.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(Register_Page.this, "please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
//                    if (pass.equals(repass)) {
//                        Boolean checkuser = DB.checkusername(user);
//                            if (!checkuser) {
//                                Boolean insert = DB.insertData(user, pass);
//                                        if (insert) {
//                                            Toast.makeText(Register_Page.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
//                                            Intent intent = new Intent(getApplicationContext(), HomePage.class);
//                                            startActivity(intent);
//                                        } else {
//                                            Toast.makeText(Register_Page.this, "Registration Failed :-(", Toast.LENGTH_SHORT).show();
//                                        }
//                            }
//                            else {
//                                Toast.makeText(Register_Page.this, "user already exists! please try again", Toast.LENGTH_SHORT).show();
//                            }
//                    }
//                    else{
//                        Toast.makeText(Register_Page.this, "password and repassword are different", Toast.LENGTH_SHORT).show();
//                    }


                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);  // if data is there or not if yes then return true else false;
                        if(!checkuser)                              // if user is not there in database the enter the block.
                        {
                            if(a)                                  // if password follows the regex patten then only enter the block;
                            {
                                Boolean insert = DB.insertData(user, pass);
                                if(insert)
                                {
                                    Toast.makeText(Register_Page.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                                    startActivity(intent);
                                }
                                else {
                                           Toast.makeText(Register_Page.this, "Insertion failed!!! because user already exists :-(", Toast.LENGTH_SHORT).show();
                                       }
                            }
                            else{
                                Toast.makeText(Register_Page.this, "password is not upto the standard", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(Register_Page.this, "user already exists! please try again", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(Register_Page.this, "password and repassword are different", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        haveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_Page.this, LoginPage.class);
                startActivity(intent);


            }
        });
    }

    private Boolean validatePassword(String pass) {
        if (!PASSWORD_PATTERN.matcher(pass).matches()) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
}