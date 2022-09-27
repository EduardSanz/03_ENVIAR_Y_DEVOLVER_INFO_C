package com.cieep.a03_enviarydevolverinformacin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cieep.a03_enviarydevolverinformacin.modelos.Direccion;
import com.cieep.a03_enviarydevolverinformacin.modelos.Usuario;

import java.util.prefs.BackingStoreException;

public class MainActivity extends AppCompatActivity {

    private EditText txtPassword;
    private EditText txtEmail;
    private Button btnDesencriptar;
    private Button btnCrearDir;

    // CONSTANTES
    private final int DIRECCIONES = 123;
    private final int TRACTORES = 133;

    // Launchers
    private ActivityResultLauncher<Intent> launcherDirecciones;
    private ActivityResultLauncher<Intent> launcherTractores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaVariables();
        inicializaLaunchers();


        btnDesencriptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = txtPassword.getText().toString();
                String email = txtEmail.getText().toString();
                Intent intent = new Intent(MainActivity.this, DesencriptarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("USER", new Usuario(email, password));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnCrearDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateDirActivity.class);
                // startActivityForResult(intent, DIRECCIONES);
                launcherDirecciones.launch(intent);
            }
        });
    }

    private void inicializaLaunchers() {
        // Registrar una actividad de retorno
        // 1. ¿Cómo lanzo la actividad hija?
        // 2. ¿Qué hago cuando mi hija termine?
        launcherDirecciones = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null){
                                Bundle bundle = result.getData().getExtras();
                                Direccion dir = (Direccion) bundle.getSerializable("DIR");
                                Toast.makeText(MainActivity.this, dir.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }

    private void inicializaVariables() {
        txtEmail = findViewById(R.id.txtEmailMain);
        txtPassword = findViewById(R.id.txtPasswordMain);
        btnDesencriptar = findViewById(R.id.btnDesencriptarMain);
        btnCrearDir = findViewById(R.id.btnCrearDirMain);
    }


    /**
     * Se activa al retornar de un startActivityForResult y dispara las acciones nesarias
     * @param requestCode -> identificador de la ventana que se ha cerrado (El tipo de dato que retorna)
     * @param resultCode -> el modo en que se ha cerrado la ventana
     * @param data -> Datos retornados (Intent con un Bundle dentro)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DIRECCIONES) {
            if (resultCode == RESULT_OK)  {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    Direccion dir = (Direccion) bundle.getSerializable("DIR");
                    Toast.makeText(this, dir.toString(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "No hay Datos", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Ventana Cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}