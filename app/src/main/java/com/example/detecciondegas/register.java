package com.example.detecciondegas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class register extends AppCompatActivity {
    EditText username, password, cpassword, restaurant;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username= (EditText) findViewById(R.id.nombreUsuario);
        password=(EditText) findViewById(R.id.password);
        cpassword=(EditText) findViewById(R.id.repeatpassword);
        restaurant=(EditText) findViewById(R.id.restaurant);
        dbHandler = new DBHandler(register.this);

    }

    public void register(View view){
        if(username.getText().toString().matches("") || password.getText().toString().matches("")|| cpassword.getText().toString().matches("")|| restaurant.getText().toString().matches("")){
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context,"Debe completar todos los campos", Toast.LENGTH_LONG );
            toast.show();
        }
        else{
            if(password.getText().toString().equals(cpassword.getText().toString())){
                Context context = getApplicationContext();
                Boolean add = this.dbHandler.addNewUser(username.getText().toString(), password.getText().toString(),restaurant.getText().toString());
                if(add){
                Toast toast = Toast.makeText(context,"Registro exitoso", Toast.LENGTH_LONG );
                toast.show();
                startActivity(new Intent(register.this, MainActivity.class));
                }
                else{
                    Toast toast = Toast.makeText(context,"El nombre de usuario ya existe", Toast.LENGTH_LONG );
                    toast.show();
                }
            }
            else{
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context,"Las contraseñas ingresadas no coinciden", Toast.LENGTH_LONG );
                toast.show();
            }
        }
    }
}