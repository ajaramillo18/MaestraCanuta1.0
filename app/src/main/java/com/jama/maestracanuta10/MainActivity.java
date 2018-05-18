package com.jama.maestracanuta10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.jama.maestracanuta10.misconducts.MisconductListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button listButton;
   //this featured was moved to the student list
    // private Button adminButton;
    private Button configButton;
    private Button misconductButton;
    private Button reportsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //greet teacher by name

        TextView welcome = (TextView) findViewById(R.id.WelcomeID);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String teacherName =  preferences.getString("teacher_name","");

        welcome.setText(welcome.getText() + " " +teacherName);



        listButton = (Button) findViewById(R.id.List_button_Id);
        //this featured was moved to the student list
        //adminButton = (Button) findViewById(R.id.Adimn_button_Id);
        configButton = (Button) findViewById(R.id.config_button_ID);
        misconductButton = (Button) findViewById(R.id.Misconduct_List_button_Id);
        reportsButton = (Button) findViewById(R.id.Reports_button_Id);



        listButton.setOnClickListener(this);
       //this featured was moved to the student list
        // adminButton.setOnClickListener(this);
        configButton.setOnClickListener(this);
        misconductButton.setOnClickListener(this);//
        reportsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.List_button_Id:
                //go to list activity
                Intent listIntent = new Intent(MainActivity.this, StudentListActivity.class);
                // listIntent.putExtra("name", "Armando");

                startActivity(listIntent);
                Toast.makeText(MainActivity.this, "List", Toast.LENGTH_LONG).show();
                break;

                /* this featured was moved to the student list
            case R.id.Adimn_button_Id:
                //go to admin activity
                Intent adminIntent = new Intent(MainActivity.this, AdminActivity.class);
                // adminIntent.putExtra("name", "Armando");
                startActivity(adminIntent);
                Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_LONG).show();
                break;
            */

            case R.id.config_button_ID:
                //go to config activity
                Intent configIntent = new Intent(MainActivity.this, SettingsActivity.class);
                // configIntent.putExtra("name", "Armando");
                startActivity(configIntent);
                Toast.makeText(MainActivity.this, "Config", Toast.LENGTH_LONG).show();
                break;

            case R.id.Misconduct_List_button_Id:
                //go to config activity
                Intent misconductIntent = new Intent(MainActivity.this, MisconductListActivity.class);
                // configIntent.putExtra("name", "Armando");
                startActivity(misconductIntent);
                Toast.makeText(MainActivity.this, "misconduct list", Toast.LENGTH_LONG).show();
                break;

            case R.id.Reports_button_Id:
                //go to config activity
                Intent reportsIntent = new Intent(MainActivity.this, ReportsActivity.class);
                // configIntent.putExtra("name", "Armando");
                startActivity(reportsIntent);
                Toast.makeText(MainActivity.this, "reports", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
