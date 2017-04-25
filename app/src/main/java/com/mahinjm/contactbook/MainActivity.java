package com.mahinjm.contactbook;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button newContact, searchContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get custom actionbar using id
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        //enable custom actionbar
        setSupportActionBar(toolbar);

        //enable the home button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        newContact = (Button) findViewById(R.id.new_contact);
        searchContact = (Button) findViewById(R.id.search_contact);

        newContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContact.class);
                startActivity(intent);
            }
        });

        searchContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchContact.class);
                startActivity(intent);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubar, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            AboutDialog();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    //showing custom dialogbox using layout
    public void AboutDialog() {
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.about_dialog);
        dialog.setTitle(R.string.dialog_title);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialog_cancel);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }


}
