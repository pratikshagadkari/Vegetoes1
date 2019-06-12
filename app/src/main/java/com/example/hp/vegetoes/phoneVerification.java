package com.example.hp.vegetoes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

import java.util.concurrent.TimeUnit;


import static java.util.concurrent.TimeUnit.*;

public class phoneVerification extends AppCompatActivity {
    EditText e1,e2;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseAuth auth;
    String verificationcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        auth=FirebaseAuth.getInstance();



    }
    public void send_sms(View v)
    {
        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {


            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.d("msg1", String.valueOf(e));
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                verificationcode=s;
                Log.d("msg1",s);
                Toast.makeText(getApplicationContext(),"Code sent to the number",Toast.LENGTH_SHORT).show();
            }
        };
        String number="+91"+e1.getText().toString();
        Log.d("number",number);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks
        );

    }
    public void signInWithPhone(PhoneAuthCredential credential)
    {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(phoneVerification.this,home.class);
                    startActivity(i);
                    finish();

                }
            }
        });
    }
    public void verify(View v)
    {
        String input_code=e2.getText().toString();

        if(!verificationcode.equals(""))
        {
            verifyPhoneNumber(verificationcode,input_code);
        }
        //verifyPhoneNumber(verificationcode,input_code);
    }
    private void verifyPhoneNumber(String verifyCode,String input_code)
    {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verifyCode,input_code);
        signInWithPhone(credential);
    }

   /* private void sendVerificationCode()
    {

        String phoneNumber=phone.getText().toString();
        if(phoneNumber.isEmpty())
        {
            phone.setError("Phone Number is required");
            phone.requestFocus();
            return;
        }
        if(phoneNumber.length()<10)
        {
            phone.setError("Please enter a valid phone");
            phone.requestFocus();
            return;

        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }
    OnVerificationStateChangedCallbacks mCallbacks=new OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent=s;
        }
    };

    private void verifyCode()
    {
        String code1=code.getText().toString();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code1);
        signInWithPhoneAuthCredential(credential);


    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Authentication Successful",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(phoneVerification.this,home.class);
                            startActivity(i);
                            finish();


                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(),"Invalid Code ",Toast.LENGTH_SHORT).show();
                            }
                                // The verification code entered was invalid


                        }
                    }
                });*/
    }






        




