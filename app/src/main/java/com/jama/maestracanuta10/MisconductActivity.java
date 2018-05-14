package com.jama.maestracanuta10;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.telephony.SmsManager;

import com.jama.maestracanuta10.data.DatabaseHandler;
import com.jama.maestracanuta10.model.Event;
import com.jama.maestracanuta10.model.Student;

public class MisconductActivity extends AppCompatActivity implements View.OnClickListener {


    private String studentId;
    private Button tardeButton;
    private Button noTareaButton;
    private Button groseriaButton;
    private String misconduct;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misconduct);

        Bundle bundle = getIntent().getExtras();
        studentId = bundle.getString("studentId");


         db = new DatabaseHandler(getApplicationContext());

        tardeButton = (Button) findViewById(R.id.TardeButtonId);
        noTareaButton = (Button) findViewById(R.id.noTareaButtonId);
        groseriaButton = (Button) findViewById(R.id.GroseriaButtonId);


        tardeButton.setOnClickListener(this);
        noTareaButton.setOnClickListener(this);
        groseriaButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TardeButtonId:

                //Toast.makeText(MisconductActivity.this, "tarde", Toast.LENGTH_LONG).show();

                misconduct = "Mal Comportamiento";
               popup();


                break;

            case R.id.noTareaButtonId:

                misconduct = "No Tarea";
                popup();
                //Toast.makeText(MisconductActivity.this, "noTarea", Toast.LENGTH_LONG).show();
                break;


            case R.id.GroseriaButtonId:
                misconduct = "Groseria";
                popup();
                //Toast.makeText(MisconductActivity.this, "groseria", Toast.LENGTH_LONG).show();
                break;
        }
    }

    //private boolean res;
    public void popup()
    {
         //res = true;

        AlertDialog.Builder builder = new AlertDialog.Builder(MisconductActivity.this);
        builder.setMessage(R.string.dialog_message_misconduct)
                .setTitle(R.string.dialog_title_misconduct)
                .setCancelable(true)
                .setIcon(R.drawable.ic_notifications_black_24dp);

        // Add the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                recordMisconduct();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MisconductActivity.this, "Falta no fue registrada", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    void recordMisconduct()
    {
        String misconductId = null;

        if(misconduct.equals("Mal Comportamiento"))
            misconductId = "1";
        else  if(misconduct.equals("No Tarea"))
        misconductId = "2";
        else
        if(misconduct.equals("Groseria"))
        misconductId = "3";


        Event event = new Event();
        event.setComments("");
        event.setIdStudent(studentId);
        event.setIdStatus("S"); //Recorder
        event.setIdMisconduct(misconductId);

        db.addEvent(event);


        //the message is sent only when the user configure it

        boolean send  = false;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        send = preferences.getBoolean("notifications_parent",false);
        Log.d("Misconduct", "notifications_parent: " + send);


        if(send){
            sendSMS();
            Toast.makeText(MisconductActivity.this, "Mensaje enviado: " + misconduct, Toast.LENGTH_LONG).show();
        }

        //finish();

    }

    private void sendSMS() {

        SmsManager smsManager = SmsManager.getDefault();

        Student student = db.getStudent(studentId);

        String falta = misconduct;
        if(misconduct.equals("No Tarea"))
            falta = "No hizo la tarea";

        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("Estimad@ ");
        sbuilder.append(student.getTutorName());
        sbuilder.append(". Le informo que ");
        sbuilder.append(student.getName());
        sbuilder.append(" cometio la siguiente falta: ");
        sbuilder.append(falta);
        //sbuilder.append(". Esto sucedió hace unos momentos.");
        sbuilder.append(". Tome las medidas pertinentes.");


        smsManager.sendTextMessage(student.getTutorPhoneNumber(), null, sbuilder.toString(), null, null);

    }



}

