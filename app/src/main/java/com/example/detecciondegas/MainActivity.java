package com.example.detecciondegas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username=(EditText)findViewById(R.id.nombreUsuario);
        password=(EditText)findViewById(R.id.password);
        dbHandler = new DBHandler(MainActivity.this);
    }
    public void loginRutActivity(View view){
        startActivity(new Intent(MainActivity.this, loginrut.class ));
    }
    public void login(View view){
        if(username.getText().toString().matches("")|| password.getText().toString().matches("")){
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context,"Existen campos sin rellenar", Toast.LENGTH_LONG );
            toast.show();
        }
        else{
            Log.d("USUARIO",dbHandler.getUser(username.getText().toString(), password.getText().toString()).toString());
            if(dbHandler.getUser(username.getText().toString(), password.getText().toString()).size()>0){
                UserModel userModel= dbHandler.getUser(username.getText().toString(), password.getText().toString()).get(0);
                Intent intent = new Intent(MainActivity.this, vistaadmin.class);
                intent.putExtra("usuario", userModel);
                startActivity(intent);
            }
            else{
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context,"Datos invalidos", Toast.LENGTH_LONG );
                toast.show();
            }
        }
    }
    public void registerForm(View view){
        startActivity(new Intent(MainActivity.this, register.class));
    }

}