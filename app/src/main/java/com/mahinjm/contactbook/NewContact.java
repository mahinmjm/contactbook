package com.mahinjm.contactbook;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class NewContact extends AppCompatActivity {

    Button saveContact, myProfile;
    EditText editTextName, editTextPhoneNo;
    SQLiteDatabase DB;
    String Name, PhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact);
        // get custom actionbar using id
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        //enable custom actionbar
        setSupportActionBar(toolbar);

        //enable back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);




        saveContact = (Button) findViewById(R.id.saveContact);
        
        DB = new SQLiteDatabase(this);

        saveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = editTextName.getText().toString().trim();
                PhoneNo = editTextPhoneNo.getText().toString().trim();

                //Toast.makeText(NewContact.this, Name +" "+PhoneNo, Toast.LENGTH_SHORT).show();

                boolean checker;
                if( !Name.isEmpty() && !PhoneNo.isEmpty()) {
                    checker = DB.insertData(editTextName.getText().toString(), editTextPhoneNo.getText().toString());

                    if(checker==true){
                        Toast.makeText(NewContact.this, "Contact Added", Toast.LENGTH_SHORT).show();
                        editTextName.setText("");
                        editTextPhoneNo.setText("");
                    }else{
                        Toast.makeText(NewContact.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(NewContact.this, "Please fill all field.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        myProfile = (Button) findViewById(R.id.my_profile);
        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewContact.this, MyProfile.class);
                startActivity(intent);
            }
        });

    }

//    //save contact to database
//    public void saveContact(View view){
//        Toast.makeText(this, editTextName.getText().toString() +" "+ editTextPhoneNo.getText().toString(), Toast.LENGTH_SHORT).show();
//    }



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
        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

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
