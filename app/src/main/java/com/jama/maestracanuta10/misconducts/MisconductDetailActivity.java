package com.jama.maestracanuta10.misconducts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.Toast;

import com.jama.maestracanuta10.R;
import com.jama.maestracanuta10.data.DatabaseHandler;

/**
 * An activity representing a single Misconduct detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MisconductListActivity}.
 */
public class MisconductDetailActivity extends AppCompatActivity implements View.OnClickListener{

   // private FloatingActionButton editButton;
    private FloatingActionButton deleteButton;
    private FloatingActionButton misconductButton;
    private String misconductId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misconduct_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

       // editButton = (FloatingActionButton) findViewById(R.id.EditButtonId);
      //  editButton.setOnClickListener(this);
        deleteButton = (FloatingActionButton) findViewById(R.id.DeleteButtonId);
        deleteButton.setOnClickListener(this);
        misconductButton = (FloatingActionButton) findViewById(R.id.MisconductButtonId);
        misconductButton.setOnClickListener(this);

        //get student id
        misconductId = getIntent().getStringExtra(MisconductDetailFragment.ARG_ITEM_ID);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(MisconductDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(MisconductDetailFragment.ARG_ITEM_ID));
            MisconductDetailFragment fragment = new MisconductDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.misconduct_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, MisconductListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
           /* case R.id.EditButtonId:
               // Intent intentAdd = new Intent(StudentDetailActivity.this, NewStudentActivity.class);
                //startActivity(intentAdd);

                Toast.makeText(MisconductDetailActivity.this, "Editar", Toast.LENGTH_LONG).show();

                Intent intentEdit = new Intent(MisconductDetailActivity.this, EditMisconductActivity.class);
                intentEdit.putExtra("misconductId", misconductId);
                startActivity(intentEdit);

                break;*/

            case R.id.DeleteButtonId:

                AlertDialog.Builder builder = new AlertDialog.Builder(MisconductDetailActivity.this);
                builder.setMessage(R.string.dialog_message_delete)
                        .setTitle(R.string.dialog_title_delete)
                        .setCancelable(true)
                        .setIcon(R.drawable.ic_notifications_black_24dp);

                // Add the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        db.deleteMisconduct(misconductId);
                        Toast.makeText(MisconductDetailActivity.this, R.string.Delete_success, Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MisconductDetailActivity.this,  R.string.Delete_aborted, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                break;

            case R.id.MisconductButtonId:

                Toast.makeText(MisconductDetailActivity.this, "Faltas...", Toast.LENGTH_LONG).show();

                Intent intentMisconduct = new Intent(MisconductDetailActivity.this, MisconductDetailActivity.class);
                intentMisconduct.putExtra("misconductId", misconductId);
                startActivity(intentMisconduct);

                break;

        }

    }


}
