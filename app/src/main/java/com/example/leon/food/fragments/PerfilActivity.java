package com.example.leon.food.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leon.food.Login;
import com.example.leon.food.MainActivity;
import com.example.leon.food.R;
import com.example.leon.food.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerfilActivity extends Fragment {
    Button btnConfirmar;
    EditText etNombre, etApellidos, etEdad;
    public PerfilActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_perfil,
                container, false);
        btnConfirmar = (Button) view.findViewById(R.id.btnConfirmar);
        etNombre = (EditText) view.findViewById(R.id.etNombre);
        etApellidos = (EditText) view.findViewById(R.id.etApellidos);
        etEdad = (EditText) view.findViewById(R.id.etEdad);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellidos = etApellidos.getText().toString();
                String edad = etEdad.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Perfil");
                FirebaseAuth yo = FirebaseAuth.getInstance();
                FirebaseUser currentUser = yo.getCurrentUser();
                String me = "";
                if (currentUser != null) {
                    me = currentUser.getEmail();
                }
                User user = new User(nombre, apellidos, edad);

                try {
                    myRef.push().setValue(user);
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "Perfil actualizado", Toast.LENGTH_SHORT);
                    toast.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return inflater.inflate(R.layout.activity_perfil, container, false);
    }
}