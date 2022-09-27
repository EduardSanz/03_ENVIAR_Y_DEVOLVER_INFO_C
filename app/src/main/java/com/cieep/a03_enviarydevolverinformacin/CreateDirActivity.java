package com.cieep.a03_enviarydevolverinformacin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cieep.a03_enviarydevolverinformacin.modelos.Direccion;

public class CreateDirActivity extends AppCompatActivity {

    private EditText txtCalle;
    private EditText txtNumero;
    private EditText txtCiudad;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dir);

        inicializaVistas();

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Direccion direccion = new Direccion(
                        txtCalle.getText().toString(),
                        Integer.parseInt(txtNumero.getText().toString()),
                        txtCiudad.getText().toString()
                );
                Bundle bundle = new Bundle();
                bundle.putSerializable("DIR", direccion);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void inicializaVistas() {
        txtCalle = findViewById(R.id.txtCalleCreateDir);
        txtNumero = findViewById(R.id.txtNumeroCreateDir);
        txtCiudad = findViewById(R.id.txtCiudadCreateDir);
        btnCrear = findViewById(R.id.btnCrearCreateDir);
    }
}