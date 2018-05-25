package com.jama.maestracanuta10.misconducts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jama.maestracanuta10.data.DatabaseHandler;
import com.jama.maestracanuta10.model.Misconduct;
import com.jama.maestracanuta10.R;

import java.util.List;

public class NewMisconductActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private Misconduct newMisconduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_misconduct);

        Button buttonAdd = (Button) findViewById(R.id.ButtonAddMisconductId);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewMisconductActivity.this, "Crear", Toast.LENGTH_LONG).show();

                EditText id = null;//(EditText) findViewById(R.id.IdTextId);
                EditText name = (EditText) findViewById(R.id.NameMisconductTextId);

                //TODO only Negative misconducts for now
                // EditText type = (EditText) findViewById(R.id.TypeTextId);
                //String sType = type.getText().toString();
                String sType = "N";

                newMisconduct =  new Misconduct(null,
                        name.getText().toString(),
                        sType);

                db = new DatabaseHandler(getApplicationContext());
                db.addMisconduct(newMisconduct);
                finish();





            }
        });


    }
}
