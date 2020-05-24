package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import java.util.Locale;

public class NovoUsuarioActivity extends AppCompatActivity {
    private static final int REQ_CODE_CAMERA = 1001;
    private EditText loginNovoUsuarioEditText;
    private EditText senhaNovoUsuarioEditText;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);
        loginNovoUsuarioEditText =
                findViewById(R.id.loginNovoUsuarioEditText);
        senhaNovoUsuarioEditText =
                findViewById(R.id.senhaNovoUsuarioEditText);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void criarNovoUsuario (View v){
        String login =
                loginNovoUsuarioEditText.
                        getEditableText().toString();
        String senha =
                senhaNovoUsuarioEditText.
                        getEditableText().toString();



        firebaseAuth.createUserWithEmailAndPassword(
                login,
                senha
        ).addOnSuccessListener(this,new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(
                        NovoUsuarioActivity.this,
                         getString(
                                R.string.cadastro_funcionou,
                                authResult.
                                        getUser().
                                        getDisplayName().
                                        toString()
                        ),
                        Toast.LENGTH_SHORT
                ).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(
                        NovoUsuarioActivity.this,
                        getString(
                                R.string.erro_inesperado
                        ),
                        Toast.LENGTH_SHORT
                ).show();
                finish();
            }
        });
    }
}
