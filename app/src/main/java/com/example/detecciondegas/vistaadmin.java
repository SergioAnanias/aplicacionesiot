package com.example.detecciondegas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class vistaadmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vistaadmin);
        // Requiere datos del sensor de gas para mostrar

    }
    public void vistanuevolocal(View view){
        startActivity(new Intent(vistaadmin.this, nuevolocal.class));

    }

}