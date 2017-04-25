package com.mahinjm.contactbook;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity {

    CheckBox checkBox,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6;
    Button backButton;
    TextView textView;

    SQLiteDatabase DB;

    boolean set_wallpaper = false,
            write_ext_storage = false,
            call_phone = false,
            send_sms = false,
            read_contacts = false,
            access_file_loc = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);

        // get custom actionbar using id
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);

        //enable custom actionbar
        setSupportActionBar(toolbar);

        //enable the home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DB = new SQLiteDatabase(this);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        //checkBox.setChecked(true);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
        checkBox6 = (CheckBox) findViewById(R.id.checkBox6);



        //textView = (TextView) findViewById(R.id.textView);

        //DB.saveSetting(set_wallpaper, write_ext_storage, call_phone, send_sms, read_contacts, access_file_loc);

        Cursor result = DB.getSettingData();

        //textView.setText(""+result+"");



        if(result.getCount()==0){
            //DB.saveSetting(set_wallpaper, write_ext_storage, call_phone, send_sms, read_contacts, access_file_loc);
        }else{

            result.moveToLast();

            do {
                if(result.getInt(0)==1){
                    checkBox.setChecked(true);
                    set_wallpaper = true;
                }else{
                    checkBox.setChecked(false);
                    set_wallpaper = false;
                }

                if(result.getInt(1)==1){
                    checkBox2.setChecked(true);
                    write_ext_storage = true;
                }else{
                    checkBox2.setChecked(false);
                    write_ext_storage = false;
                }

                if(result.getInt(2)==1){
                    checkBox3.setChecked(true);
                    call_phone = true;
                }else{
                    checkBox3.setChecked(false);
                    call_phone = false;
                }

                if(result.getInt(3)==1){
                    checkBox4.setChecked(true);
                    send_sms = true;
                }else{
                    checkBox4.setChecked(false);
                    send_sms = false;
                }

                if(result.getInt(4)==1){
                    checkBox5.setChecked(true);
                    read_contacts = true;
                }else{
                    checkBox5.setChecked(false);
                    read_contacts = false;
                }

                if(result.getInt(5)==1){
                    checkBox6.setChecked(true);
                    access_file_loc = true;
                }else {
                    checkBox6.setChecked(false);
                    access_file_loc = false;
                }

            } while (result.moveToNext());
        }


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    set_wallpaper = true;
                } else {
                    set_wallpaper = false;
                }
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox2.isChecked()) {
                    write_ext_storage = true;
                } else {
                    write_ext_storage = false;
                }
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox3.isChecked()) {
                    call_phone = true;
                } else {
                    call_phone = false;
                }
            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox4.isChecked()) {
                    send_sms = true;
                } else {
                    send_sms = false;
                }
            }
        });
        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox5.isChecked()) {
                    read_contacts = true;
                } else {
                    read_contacts = false;
                }
            }
        });
        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox6.isChecked()) {
                    access_file_loc = true;
                } else {
                    access_file_loc = false;
                }
            }
        });


        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MyProfile.this, " "+ set_wallpaper +" "+ write_ext_storage +" "+ call_phone +" "+ send_sms +" "+ read_contacts +" "+ access_file_loc +" ", Toast.LENGTH_SHORT).show();
                boolean checker = DB.saveSetting(set_wallpaper, write_ext_storage, call_phone, send_sms, read_contacts, access_file_loc);

                if (checker == true)
                    Toast.makeText(MyProfile.this, "Save Settings", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MyProfile.this, "Something Wrong", Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
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