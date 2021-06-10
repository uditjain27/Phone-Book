package com.java.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ViewContact extends AppCompatActivity {

    String[] mobileArray;
    boolean doubleBackToExitPressedOnce = false;
    List<Contact> contact;
    private static final int STORAGE_PERMISSION_CODE = 101;
    private static final int CALL_PERMISSION_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        try {
            Class.forName("Manager");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        checkPermission();
        Manager.sortByName();
        contact = Manager.contacts;

        if(contact == null)
            mobileArray = new String[0];
        else {
            mobileArray = new String[contact.size()];
            for (int ii = 0; ii < contact.size(); ii++) {
                mobileArray[ii] = contact.get(ii).getName();
            }
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main, mobileArray);

        final ListView listView = (ListView) findViewById(R.id.contactlist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value = listView.getAdapter().getItem(position).toString();
                Intent myIntent = new Intent(view.getContext(), MenuOptions.class);
                myIntent.putExtra("STRING_I_NEED", value);
                startActivity(myIntent);
            }
        });
    }

    public void addnewcontact(View view){
        Intent myIntent = new Intent(view.getContext(), AddNewContact.class);
        startActivity(myIntent);
    }

    public void searchcontact(View view){
        Intent myIntent = new Intent(view.getContext(), SearchByName.class);
        startActivity(myIntent);
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(getApplicationContext(), "Press back again to exit...", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

//    public void checkPermission(String permission, int requestCode)
//    {
//        if (ContextCompat.checkSelfPermission(ViewContact.this, permission) == PackageManager.PERMISSION_DENIED) {
//
//            // Requesting the permission
//            ActivityCompat.requestPermissions(ViewContact.this, new String[] { permission }, requestCode);
//        }
//    }
//
//    // This function is called when the user accepts or decline the permission.
//    // Request Code is used to check which permission called this function.
//    // This request code is provided when the user is prompt for permission.
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
//    {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == CALL_PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(ViewContact.this, "Call Permission Granted",Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(ViewContact.this, "Call Permission Denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//        else if (requestCode == STORAGE_PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(ViewContact.this,"Storage Permission Granted", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(ViewContact.this,"Storage Permission Denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
public void checkPermission() {
    if (Build.VERSION.SDK_INT >= 23) {
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE,}, 1);
        }
    }
}


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!(requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)) {

            checkPermission();
        }
    }
}