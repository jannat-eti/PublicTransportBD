package com.example.jsonconvert;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText inputEmailET, inputPasswordET, inputConfirmPasswordET;
    Button registerBTN;
    Button  busname;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        init();
    }

    private void init() {

        inputEmailET = (EditText) findViewById(R.id.ET_input_email);
        inputPasswordET = (EditText) findViewById(R.id.ET_input_password);
        inputConfirmPasswordET = (EditText) findViewById(R.id.ET_input_confirm_password);
        registerBTN = (Button) findViewById(R.id.BTN_register);

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(RegisterActivity.this);
                dialog.setMessage("Please wait");
                dialog.setCancelable(false);
                dialog.show();
                if (checkValidity()){

                    String email = inputEmailET.getText().toString();
                    String password = inputConfirmPasswordET.getText().toString();

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()){

                                        dialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Registeration is successfull !!!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));

                                    }else {

                                        dialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, ""+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }

    private boolean checkValidity(){

        if (inputEmailET.getText().toString().equals("")){

            inputEmailET.setError("Please enter a valid email");
            return  false;

        }else if (inputPasswordET.getText().toString().equals("")){

            inputPasswordET.setError("Please enter a valid email");
            return  false;

        }else if (inputConfirmPasswordET.getText().toString().equals("")){

            inputConfirmPasswordET.setError("Please enter a valid email");
            return  false;

        }else if (! inputConfirmPasswordET.getText().toString().equals(inputPasswordET.getText().toString())){

            inputConfirmPasswordET.setError("Please enter a valid email");
            return  false;

        }else {

            return  true;
        }
    }
}
