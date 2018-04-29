package com.jama.maestracanuta10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jama.maestracanuta10.data.DatabaseHandler;
import com.jama.maestracanuta10.model.Student;

import java.util.List;

public class NewStudentActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private Student newStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        Button buttonAdd = (Button) findViewById(R.id.ButtonAddId);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewStudentActivity.this, "Crear", Toast.LENGTH_LONG).show();

                EditText id = (EditText) findViewById(R.id.IdTextId);
                EditText name = (EditText) findViewById(R.id.NameTextId);
                EditText phone = (EditText) findViewById(R.id.PhoneTextId);
                EditText tutorName = (EditText) findViewById(R.id.TutorTextId);
                EditText tutorEmail = (EditText) findViewById(R.id.EmailTextId);

                newStudent =  new Student(id.getText().toString(),
                                          name.getText().toString(),
                                          phone.getText().toString(),
                                          tutorName.getText().toString(),
                                          tutorEmail.getText().toString());

                db = new DatabaseHandler(getApplicationContext());
                db.addStudent(newStudent);
                finish();

     /*           List<Student> stList = db.getAllStudents();

                for(Student st : stList)
                {
                    Log.d("test","Nombre:"+ st.getName());
                }*/



            }
        });


    }
}
