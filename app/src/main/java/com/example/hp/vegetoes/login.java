package com.example.hp.vegetoes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.vegetoes.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class login extends AppCompatActivity {
    FirebaseAuth auth;
    EditText phnno,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phnno=findViewById(R.id.phnno);
        password=findViewById(R.id.password);
        login =findViewById(R.id.login);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(login.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();
                String number = phnno.getText().toString();
                String pass = password.getText().toString();
                if (TextUtils.isEmpty(number)) {
                    mDialog.dismiss();
                    Toast.makeText(login.this, "Please enter the details", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(pass)) {
                    mDialog.dismiss();
                    Toast.makeText(login.this, "Please enter the details", Toast.LENGTH_SHORT).show();
                    return;
                }
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phnno.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(phnno.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(password.getText().toString())) {
                                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(login.this, home.class);
                                startActivity(i);
                                finish();

                            } else {
                                mDialog.dismiss();
                                Toast.makeText(login.this, "Entered Details are not correct", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(login.this,"User Does not exists...Please SignUp",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }



    public void register(View view) {
        Intent i=new Intent(this,register.class);
        startActivity(i);

    }

    public void change(View view) {
        Intent i=new Intent(login.this,forgotPassword.class);
        startActivity(i);
    }
}
