package com.example.leon.food;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registro extends AppCompatActivity {
    Button btnRegistrar;
    EditText etEmail, etContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        final EditText etEmail = (EditText)findViewById(R.id.etEmail);
        final EditText etContraseña = (EditText)findViewById(R.id.etContraseña);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().equals("") || etContraseña.getText().toString().equals("")){
                    Toast toast = Toast.makeText(Registro.this, "Rellene todos los campos", Toast.LENGTH_SHORT);
                    toast.show();
                }else if(etContraseña.getText().toString().trim().length() < 6){
                    Toast toast = Toast.makeText(Registro.this, "Longitud contraseña minima 6 caracteres", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmail.getText().toString(), etContraseña.getText().toString())
                            .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Registro.this, "Fallo al registrar usuario, intentelo de nuevo",
                                                Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(Registro.this, "Registrado correctamente",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Registro.this, MainActivity.class));
                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }
}