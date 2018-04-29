package com.jama.maestracanuta10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button listButton;
    private Button adminButton;
    private Button configButton;
    private Button misconductButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listButton = (Button) findViewById(R.id.List_button_Id);
        adminButton = (Button) findViewById(R.id.Adimn_button_Id);
        configButton = (Button) findViewById(R.id.Config_button_Id);
        misconductButton = (Button) findViewById(R.id.Misconduct_button_Id);


        listButton.setOnClickListener(this);
        adminButton.setOnClickListener(this);
        configButton.setOnClickListener(this);
        misconductButton.setOnClickListener(this);
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

            case R.id.Adimn_button_Id:
                //go to admin activity
                Intent adminIntent = new Intent(MainActivity.this, AdminActivity.class);
                // adminIntent.putExtra("name", "Armando");
                startActivity(adminIntent);
                Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_LONG).show();
                break;


            case R.id.Config_button_Id:
                //go to config activity
                Intent configIntent = new Intent(MainActivity.this, SettingsActivity.class);
                // configIntent.putExtra("name", "Armando");
                startActivity(configIntent);
                Toast.makeText(MainActivity.this, "Config", Toast.LENGTH_LONG).show();
                break;

            case R.id.Misconduct_button_Id:
                //go to config activity
                Intent misconductIntent = new Intent(MainActivity.this, MisconductListActivity.class);
                // configIntent.putExtra("name", "Armando");
                startActivity(misconductIntent);
                Toast.makeText(MainActivity.this, "misconduct list", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
