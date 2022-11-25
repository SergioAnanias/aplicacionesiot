package com.example.detecciondegas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDeleteActivity extends AppCompatActivity {
    public localModel localModel;
    private DBHandler databaseHelper;
    private UserModel userModel;
    private EditText editDireccion, editRut, editNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        Intent intent = getIntent();
        localModel = (localModel) intent.getSerializableExtra("local");
        userModel = (UserModel) intent.getSerializableExtra("usuario");
        databaseHelper = new DBHandler(this);
        editDireccion = (EditText) findViewById(R.id.direccion);
        editNombre = (EditText) findViewById(R.id.nombreAdmin);
        editRut = (EditText) findViewById(R.id.rutAdmin);
        editDireccion.setText(localModel.getDireccion());
        editNombre.setText(localModel.getNombre());
    }

    public void updateLocal(View view) {
        if (editDireccion.getText().toString().matches("") || editNombre.getText().toString().matches("")) {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Existen campos sin rellenar", Toast.LENGTH_LONG);
            toast.show();
        } else {
            databaseHelper.updateLocal(editDireccion.getText().toString(), editNombre.getText().toString(), localModel.getRut());
            Toast.makeText(UpdateDeleteActivity.this, "Actualización exitosa", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateDeleteActivity.this, vistaadmin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("usuario", userModel);
            startActivity(intent);
        }
    }

    public void deleteLocal(View view){
        databaseHelper.deleteLocal(localModel.getRut());
        Toast.makeText(UpdateDeleteActivity.this, "Borrado exitoso", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdateDeleteActivity.this, vistaadmin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("usuario", userModel);
        startActivity(intent);
    }
}