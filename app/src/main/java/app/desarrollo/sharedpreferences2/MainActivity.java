package app.desarrollo.sharedpreferences2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtEmailUsuario,txtContrasena;
    Button btnIngresar;
    SharedPreferences preferencias_ingreso;
    SharedPreferences.Editor editor_preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmailUsuario = (EditText)findViewById(R.id.txtEmailUsuario);
        txtContrasena = (EditText)findViewById(R.id.txtContrasena);
        btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);

        preferencias_ingreso = getSharedPreferences("preferencias_ingreso",Context.MODE_PRIVATE);
        editor_preferencias = preferencias_ingreso.edit();

        setDatosPreferencias();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIngresar:
                if(setError()) entrar();
                break;
        }
    }

    public boolean setError(){
        int aux = 0;
        if(txtEmailUsuario.getText().toString().length()==0){
            txtEmailUsuario.setError("Campo requerido!");
            aux+=1;
        }
        if(txtContrasena.getText().toString().length()==0){
            txtContrasena.setError("Campo requerido!");
            aux+=1;
        }
        return (aux==0);
    }
    public void entrar(){
        String email = txtEmailUsuario.getText().toString();
        String contrasena = txtContrasena.getText().toString();

        Intent intent = new Intent(getApplicationContext(),PrincipalActivity.class);
        intent.putExtra("email",email);
        intent.putExtra("contrasena",contrasena);
        editor_preferencias.putString("email",email);
        editor_preferencias.putString("contrasena",contrasena);
        editor_preferencias.commit();
        startActivity(intent);
    }

    public void setDatosPreferencias(){
        if(preferencias_ingreso.contains("email")){
            String email = preferencias_ingreso.getString("email","");
            txtEmailUsuario.setText(email);
        }
        if(preferencias_ingreso.contains("contrasena")){
            String contrasena = preferencias_ingreso.getString("contrasena","");
            txtContrasena.setText(contrasena);
        }

    }
}
