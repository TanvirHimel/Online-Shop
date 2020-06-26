package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputName, InputPhoneNumber, InputPassword;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccountButton = (Button) findViewById(R.id.register_btn);
        InputName = (EditText) findViewById(R.id.register_username_input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phn_number_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        loadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });

    }

    private void CreateAccount() {

        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please write your name..",Toast.LENGTH_LONG);
        }

        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please write your mobile number.",Toast.LENGTH_LONG);
        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please write your password..",Toast.LENGTH_LONG);
        }

        else{

            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please Wait while we are checking Credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            
            validatePhoneNumber(name, phone, password);
        }
    }

    private void validatePhoneNumber(String name, final String phone, String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (!(dataSnaphot))
                {

                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "This "+ phone + " number alreday exisits", Toast.LENGTH_SHORT);
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please try again with another phone number", Toast.LENGTH_SHORT);

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
