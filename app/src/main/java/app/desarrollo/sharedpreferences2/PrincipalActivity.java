package app.desarrollo.sharedpreferences2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtUsuarioIngreso, txtContrasenaIngreso;
    Button btnBorrarDatos;

    SharedPreferences preferencias_ingreso;
    SharedPreferences.Editor preferencias_editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtUsuarioIngreso = (TextView)findViewById(R.id.txtUsuarioIngreso);
        txtContrasenaIngreso = (TextView)findViewById(R.id.txtContrasenaIngreso);
        btnBorrarDatos = (Button)findViewById(R.id.btnBorrarDatos);
        btnBorrarDatos.setOnClickListener(this);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String contrasena = intent.getStringExtra("contrasena");
        txtUsuarioIngreso.setText(email);
        txtContrasenaIngreso.setText(contrasena);

        preferencias_ingreso = getSharedPreferences("preferencias_ingreso", Context.MODE_PRIVATE);
        preferencias_editor = preferencias_ingreso.edit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBorrarDatos:
                preferencias_editor.clear();
                Toast.makeText(getApplicationContext(), "Preferencias eliminadas", Toast.LENGTH_SHORT).show();
                preferencias_editor.commit();
                break;
        }
    }
}
