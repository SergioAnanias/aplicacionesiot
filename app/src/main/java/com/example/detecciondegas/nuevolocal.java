package com.example.detecciondegas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class nuevolocal extends AppCompatActivity {
    EditText direccion, nombreAdmin, RUTAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevolocal);
        direccion = (EditText) findViewById(R.id.direccion);
        nombreAdmin = (EditText) findViewById(R.id.nombreAdmin);
        RUTAdmin=(EditText) findViewById(R.id.rutAdmin);
    }
    public void nuevoLocal(View view){
        if(direccion.getText().toString().matches("")||nombreAdmin.getText().toString().matches("")||RUTAdmin.getText().toString().matches("")){
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context,"Existen campos sin rellenar", Toast.LENGTH_LONG );
            toast.show();
        }
        else{
            if(validateRut(RUTAdmin.getText().toString())){
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context,"Registro exitoso", Toast.LENGTH_LONG );
                toast.show();
                // Registrar en base de datos
                startActivity(new Intent(nuevolocal.this, vistaadmin.class));

            }
            else{
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context,"El rut ingresado no es valido", Toast.LENGTH_LONG );
                toast.show();

            }
        }

    }
    private boolean validateRut(String rut){
        String rutregex="^(\\d{1,2})(\\d{3})(\\d{3})-(\\w{1})$";
        Pattern p = Pattern.compile(rutregex);
        Matcher m = p.matcher(rut);
        return m.matches();
    }
}