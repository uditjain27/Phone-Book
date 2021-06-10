package com.java.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddNewContact extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] s1 = {"Select", "Phone", "Google", "Samsung Cloud"};
    String[] s2 = {"Select", "Home", "Work", "Personal"};
    Contact i = new Contact(" ", " ", " ", "");
    String pos = "Personal";
    String newString;
    List con = Manager.contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                newString = extras.getString("STRING_I_NEED");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        Spinner spi1 = findViewById(R.id.spinner1);
        spi1.setOnItemSelectedListener(this);
        ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, s1);
        a.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spi1.setAdapter(a);

        Spinner spi2 = findViewById(R.id.spinner2);
        spi2.setOnItemSelectedListener(this);
        ArrayAdapter b = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, s2);
        b.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spi2.setAdapter(b);


        if (newString != null) {
            EditText ed1 = findViewById(R.id.name);
            EditText ed2 = findViewById(R.id.number);
            EditText ed3 = findViewById(R.id.mailid);
            Contact c = Manager.getdetails(newString);
            ed1.setText(c.getName());
            ed2.setText(c.getMobile());
            ed3.setText(c.getEmail());
            int spinnerPosition = b.getPosition(c.getCategory());
            spi2.setSelection(spinnerPosition);
            Button btn = findViewById(R.id.savebutton);
            btn.setText("Update");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("position", String.valueOf(position));
        pos = s2[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void save(View v) {
        Button btn = findViewById(R.id.savebutton);
        if (btn.getText().toString().toUpperCase().equals("SAVE")) {
            EditText ed1 = findViewById(R.id.name);
            EditText ed2 = findViewById(R.id.number);
            EditText ed3 = findViewById(R.id.mailid);

            String ret = Manager.add(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString(), pos);
            Toast.makeText(getApplicationContext(), ret, Toast.LENGTH_LONG).show();
            if (ret == "Contact Created") {
                Intent myIntent = new Intent(v.getContext(), ViewContact.class);
                startActivity(myIntent);
            }
        } else if (btn.getText().toString().toUpperCase().equals("UPDATE")) {
            EditText ed1 = findViewById(R.id.name);
            EditText ed2 = findViewById(R.id.number);
            EditText ed3 = findViewById(R.id.mailid);

            Manager.update(newString, ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString(), pos);
            Intent myIntent = new Intent(v.getContext(), ViewContact.class);
            startActivity(myIntent);
        }
    }
}


//    public static void writeRecordsFromFile1(List<Contact> con){
//        FileOutputStream fos;
//        ObjectOutputStream oos=null;
//        try{
//            fos = new AppCompatActivity().getApplicationContext().openFileOutput("myRecords.txt", Context.MODE_PRIVATE);
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(con);
//            oos.close();
//            Toast.makeText(getApplicationContext(), "Saved your text", Toast.LENGTH_LONG).show();
//        }catch(Exception e){
//            Log.e(" ", "Cant save records"+e.getMessage());
//        }
//        finally{
//            if(oos!=null)
//                try{
//                    oos.close();
//                }catch(Exception e){
//                    Log.e(" ", "Error while closing stream "+e.getMessage());
//                }
//        }
//    }
//    public static List<Contact> readRecordsFromFile1(){
//        List<Contact> contact = null;
//        if(contact ==null){
//            contact = new ArrayList<>();
//        }
//        FileInputStream fin;
//        ObjectInputStream ois=null;
//        try{
//            fin = getApplicationContext().openFileInput("myRecords.txt");
//            ois = new ObjectInputStream(fin);
//            List<Contact> records = (List<Contact>) ois.readObject();
//            ois.close();
//            Log.v(" ", "Records read successfully");
//            return records;
//        }catch(Exception e){
//            Log.e(" ", "Cant read saved records"+e.getMessage());
//            return null;
//        }
//        finally{
//            if(ois!=null)
//                try{
//                    ois.close();
//                }catch(Exception e){
//                    Log.e(" ", "Error in closing stream while reading records"+e.getMessage());
//                }
//        }
//    }





//        try{
//            File apkStorage = new File(Environment.getExternalStorageDirectory() + "/pro");
//            if (!apkStorage.exists()) {
//                apkStorage.mkdir();
//                Log.i("ll", "Directory Created.");
//                Log.i("lllll", apkStorage.getPath());
//            } else
//                Log.i("", " no directory created");
//            File outputFile = new File(apkStorage, "phonebook.txt");
//            if (!outputFile.exists()) {
//                outputFile.createNewFile();
//                Log.i("TAG", "File Created");
//            }else
//                Log.i("", "file not created");
//
//            FileOutputStream f= new FileOutputStream(outputFile, true);
//            OutputStreamWriter osw1 = new OutputStreamWriter(f);
//            osw1.append(i.getMobile());
//            osw1.append(" ");
//            osw1.append(i.getEmail());
//            osw1.append(" ");
//            osw1.append(i.getCategory());
//            osw1.append(" ");
//            osw1.append(i.getName());
//            osw1.append(" ");
//            osw1.append("\n");
//            osw1.flush();
//            osw1.close();
//        }
//        catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//            FileInputStream fI = new FileInputStream(outputFile);
//            InputStreamReader isr1 = new InputStreamReader(fI);
//            BufferedReader b1 = new BufferedReader(isr1);
//            String m = b1.readLine();
//            while (m != null) {
//                ed6.append(m);
//                Log.i("File Reading stuff", "success = " + m);
//                m = b1.readLine();
//            }
