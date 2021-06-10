package com.java.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.List;

public class SearchByName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);
    }

    public void radioclickedname(View view){
        ListView t= findViewById(R.id.displaysearchedcontact);
        t.setVisibility(View.INVISIBLE);
        LinearLayout l1=findViewById(R.id.numberbox);
        l1.setVisibility(View.INVISIBLE);
        LinearLayout l2=findViewById(R.id.namebox);
        l2.setVisibility(View.VISIBLE);
        Button bsearch = findViewById(R.id.buttonsearch);
        bsearch.setVisibility(View.VISIBLE);
        RadioButton rbn = findViewById(R.id.rbnumber);
        rbn.setChecked(false);
    }

    public void radioclickednumber(View view){
        ListView t= findViewById(R.id.displaysearchedcontact);
        t.setVisibility(View.INVISIBLE);
        LinearLayout l1=findViewById(R.id.namebox);
        l1.setVisibility(View.INVISIBLE);
        LinearLayout l2=findViewById(R.id.numberbox);
        l2.setVisibility(View.VISIBLE);
        Button bsearch = findViewById(R.id.buttonsearch);
        bsearch.setVisibility(View.VISIBLE);
        RadioButton rbn = findViewById(R.id.rbname);
        rbn.setChecked(false);
    }

    public void search(View view){

        EditText ed1= findViewById(R.id.searchbyname);
        EditText ed2= findViewById(R.id.searchbynumber);
        String[] mobileArray;
        RadioButton rbname = findViewById(R.id.rbname);
        RadioButton rbnumber = findViewById(R.id.rbnumber);
        if(rbname.isChecked() == true){
            List<Contact> contact = Manager.viewByName(ed1.getText().toString());
            mobileArray = new String[contact.size()];
            for (int i = 0; i < contact.size(); i++) {
                mobileArray[i] = contact.get(i).getName();
            }

            final ListView t= findViewById(R.id.displaysearchedcontact);
            t.setVisibility(View.VISIBLE);
            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main2, mobileArray);
            t.setAdapter(adapter);
            t.setOnItemClickListener((parent, view12, position, id) -> {

                String value = t.getAdapter().getItem(position).toString();
                Intent myIntent = new Intent(view12.getContext(), MenuOptions.class);
                myIntent.putExtra("STRING_I_NEED", value);
                startActivity(myIntent);
            });
        }else if(rbnumber.isChecked() == true){
            List<Contact> contact = Manager.viewByNumber(ed2.getText().toString());
            mobileArray = new String[contact.size()];
            for (int i = 0; i < contact.size(); i++) {
                mobileArray[i] = contact.get(i).getName();
            }

            final ListView t= findViewById(R.id.displaysearchedcontact);
            t.setVisibility(View.VISIBLE);
            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main, mobileArray);
            t.setAdapter(adapter);
            t.setOnItemClickListener((parent, view1, position, id) -> {

                String value = t.getAdapter().getItem(position).toString();
                Intent myIntent = new Intent(view1.getContext(), MenuOptions.class);
                myIntent.putExtra("STRING_I_NEED", value);
                startActivity(myIntent);
            });
        }
    }
}