package com.cieep.a03_enviarydevolverinformacin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.cieep.a03_enviarydevolverinformacin.modelos.Usuario;

public class DesencriptarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desencriptar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){

            Usuario user = (Usuario) bundle.getSerializable("USER");
            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}