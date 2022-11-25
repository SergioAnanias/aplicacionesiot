package com.example.detecciondegas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginrut extends AppCompatActivity {
    EditText rut;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginrut);
        rut = (EditText) findViewById(R.id.RUT);
        dbHandler = new DBHandler(loginrut.this);

    }
    public void loginRUT(View view){
        if(validateRut(rut.getText().toString())){
            ArrayList<localModel> arrayListlocalModel = dbHandler.getLocal(rut.getText().toString());
            if(arrayListlocalModel.size()>0){
                Intent intent = new Intent(loginrut.this,vistaencargado.class);
                intent.putExtra("local",arrayListlocalModel.get(0));
                startActivity(intent);
            }
            else{
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context,"RUT no valido", Toast.LENGTH_LONG );
                toast.show();
            }
        }
        else {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context,"RUT no valido", Toast.LENGTH_LONG );
            toast.show();
        }

    }
    private boolean validateRut(String rut){
        String rutregex="^(\\d{1,2})(\\d{3})(\\d{3})-(\\w{1})$";
        Pattern p = Pattern.compile(rutregex);
        Matcher m = p.matcher(rut);
        return m.matches();
    }
}