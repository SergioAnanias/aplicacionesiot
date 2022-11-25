package com.example.detecciondegas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class vistaadmin extends AppCompatActivity {
    public UserModel userModel;
    private DBHandler dbHandler;
    private LocalesAdapter localesAdapter;
    private ArrayList<localModel> localModelArrayList;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vistaadmin);
        Intent intent = getIntent();
        dbHandler=new DBHandler(this);
        listView= (ListView) findViewById(R.id.lv);
        userModel = (UserModel) intent.getSerializableExtra("usuario");
        localModelArrayList = dbHandler.getLocalDueno(userModel.getId());
        localesAdapter = new LocalesAdapter(this, localModelArrayList);
        listView.setAdapter(localesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(vistaadmin.this,UpdateDeleteActivity.class);
                intent.putExtra("local", localModelArrayList.get(position));
                intent.putExtra("usuario", userModel);
                startActivity(intent);
            }
        });
    }

    public void vistanuevolocal(View view){
        Intent intent = new Intent(vistaadmin.this,nuevolocal.class);
        intent.putExtra("usuario", userModel);
        startActivity(intent);
    }

}