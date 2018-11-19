package com.example.leon.food;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button btnLogin;
    EditText etEmail, etContraseña;
    TextView tvRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        final EditText etEmail = (EditText)findViewById(R.id.etEmail);
        final EditText etContraseña = (EditText)findViewById(R.id.etContraseña);
        TextView tvRegistrar = (TextView)findViewById(R.id.tvRegistrar);

        tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);


            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().equals("") || etContraseña.getText().toString().equals("")){
                    Toast toast = Toast.makeText(Login.this, "Rellene todos los campos", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmail.getText().toString(), etContraseña.getText().toString())
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Login.this, "Usuario y/o contraseña incorrectos",
                                                Toast.LENGTH_SHORT).show();
                                    }else{
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }
}