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

public class EditStudentActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private Student newStudent;
    private Student oldStudent;
    private String studentId;

    EditText id;
    EditText name;
    EditText phone;
    EditText tutorName;
    EditText tutorEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        db = new DatabaseHandler(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        studentId = bundle.getString("studentId");

        id = (EditText) findViewById(R.id.IdEditTextId);
        name = (EditText) findViewById(R.id.NameEditTextId);
        phone = (EditText) findViewById(R.id.PhoneEditTextId);
        tutorName = (EditText) findViewById(R.id.TutorEditTextId);
        tutorEmail = (EditText) findViewById(R.id.EmailEditTextId);

        oldStudent = db.getStudent(studentId);

        id.setText(oldStudent.getId());
        name.setText(oldStudent.getName());
        phone.setText(oldStudent.getTutorPhoneNumber());
        tutorName.setText(oldStudent.getTutorName());
        tutorEmail.setText(oldStudent.getTutorEmail());

        id.setEnabled(false);
        Button buttonEdit = (Button) findViewById(R.id.ButtonEditId);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditStudentActivity.this, "Crear", Toast.LENGTH_LONG).show();



                newStudent =  new Student(studentId,
                        name.getText().toString(),
                        phone.getText().toString(),
                        tutorName.getText().toString(),
                        tutorEmail.getText().toString());


                db.updateStudent(newStudent);

                Log.d("test","Editado: "+ newStudent.getName());
                finish();







            }
        });



    }
}
