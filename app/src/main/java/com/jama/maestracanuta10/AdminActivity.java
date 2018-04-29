package com.jama.maestracanuta10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jama.maestracanuta10.data.DatabaseHandler;
import com.jama.maestracanuta10.model.Event;

import java.util.List;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonAdd;
    private Button buttonDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

         buttonAdd = (Button) findViewById(R.id.buttonNewId);

        buttonAdd.setOnClickListener(this);


        buttonDelete = (Button) findViewById(R.id.buttonDeleteId);

        buttonDelete.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.buttonNewId:
                Intent intentAdd = new Intent(AdminActivity.this, NewStudentActivity.class);
                startActivity(intentAdd);

                break;

            case R.id.buttonDeleteId:

                Log.d("test","XXXXXXXXXXXXXXXXXXXXX");
                 DatabaseHandler db;
                db = new DatabaseHandler(getApplicationContext());
                      List<Event> stList = db.getAllEvents();

                for(Event st : stList)
                {
                    Log.d("test","getId:"+ st.getId());
                    Log.d("test","getIdMisconduct:"+ st.getIdMisconduct());
                    Log.d("test","getIdStudent:"+ st.getIdStudent());
                    Log.d("test","getIdStatus:"+ st.getIdStatus());
                    Log.d("test","getDate:"+ st.getDate());
                }

                break;

            case R.id.buttonEdit:

                Toast.makeText(AdminActivity.this, "buttonEdit", Toast.LENGTH_LONG).show();
                break;
        }


    }
}
