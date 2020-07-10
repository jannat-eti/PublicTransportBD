package com.example.jsonconvert;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText inputEmailET, inputPasswordET;
    Button loginBTN;
    TextView registerTV;

    FirebaseAuth firebaseAuth;

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        firebaseAuth = FirebaseAuth.getInstance();

        init();


//       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null){
//
//             startActivity(new Intent(MainActivity.this, HomeActivity.class));
//        }


    }

    private void init() {

        inputEmailET = (EditText) findViewById(R.id.ET_input_email);
        inputPasswordET = (EditText) findViewById(R.id.ET_input_password);
        loginBTN = (Button) findViewById(R.id.BTN_login);
        registerTV = (TextView) findViewById(R.id.TV_register);

        dialog = new ProgressDialog(MainActivity.this);


        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.setMessage("Please wait");
                dialog.setCancelable(true);
                dialog.show();

                if (checkValidity()){

                    String email = inputEmailET.getText().toString();
                    String password = inputPasswordET.getText().toString();

                    if (email.equals("admin") && password.equals("admin")){

                        startActivity(new Intent(MainActivity.this, AdminSelectActivity.class));

                    }else {

                        if (email.equals("admin") && password.equals("admin")){

                            startActivity(new Intent(MainActivity.this, AdminSelectActivity.class));

                        }else {

                            loginWithIdPass(email, password);
                        }

                    }

                }
            }
        });


        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, RegisterActivity.class));

            }
        });
    }


    private void loginWithIdPass(String email, String password){

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));

                        }else {

                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, ""+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    private boolean checkValidity(){

        if (inputEmailET.getText().toString().equals("")){

            inputEmailET.setError("Please enter a valid email");
            return  false;

        }else if (inputPasswordET.getText().toString().equals("")){

            inputPasswordET.setError("Please enter a valid password");
            return  false;

        }else {

            return  true;
        }
    }


}



