package com.mahinjm.contactbook;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SearchContact extends AppCompatActivity {


    EditText editTextName;
    TextView dbtextID, dbtextName, dbtextPhoneNo;
    Button searchButton;

    SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_contact);

        // get custom actionbar using id
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        //enable custom actionbar
        setSupportActionBar(toolbar);

        //enable back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextName = (EditText) findViewById(R.id.editTextName);
        searchButton = (Button) findViewById(R.id.searchContact);
        dbtextID = (TextView) findViewById(R.id.dbtextID);
        dbtextName = (TextView) findViewById(R.id.dbtextName);
        dbtextPhoneNo = (TextView) findViewById(R.id.dbtextPhoneNo);

        DB = new SQLiteDatabase(this);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor result = DB.showData(editTextName.getText().toString().trim());
                //Cursor result = DB.showData();

                if(result.getCount()==0){
                    Toast.makeText(SearchContact.this,"No data found.", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    result.moveToFirst();

                    do{
                        dbtextID.setText(result.getString(0));
                        dbtextName.setText(result.getString(1));
                        dbtextPhoneNo.setText(result.getString(2));
                    }while (result.moveToNext());
//
//                    StringBuffer stringBuffer = new StringBuffer();
//                    do{
//                        stringBuffer.append("ID " + result.getString(0) + "\n");
//                        stringBuffer.append("Name: " + result.getString(1) + "\n");
//                        stringBuffer.append("Phone No: " + result.getString(2) + "\n");
//                    }while (result.moveToNext());
//
//                    Display(stringBuffer.toString());
                }

            }
        });

    }

//
//    public void Display(String data){
//        textID.setText(data);
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
