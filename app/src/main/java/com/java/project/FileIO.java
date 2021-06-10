package com.java.project;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO extends AddNewContact {

    public static void write() {
        FileOutputStream fos;
        ObjectOutputStream oos=null;
        try{
            File apkStorage = new File(Environment.getExternalStorageDirectory() + "/project");
            if (!apkStorage.exists()) {
                apkStorage.mkdir();
                Log.i("ll", "Directory Created.");
                Log.i("lllll", apkStorage.getPath());
            } else
                Log.i("", " directory already exist");
            File outputFile = new File(apkStorage, "phonebook.txt");
            if (!outputFile.exists()) {
                outputFile.createNewFile();
                Log.i("TAG", "File Created");
            }else
                Log.i("", "file already exist");

            fos= new FileOutputStream(outputFile);
            //fos = new AppCompatActivity().getApplicationContext().openFileOutput("myRecords.txt", Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(Manager.contacts);
            oos.close();
            Toast.makeText(new AppCompatActivity().getApplicationContext(), "Saved your text", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Log.e(" ", "Cant save records"+e.getMessage());
        }
        finally{
            if(oos!=null)
                try{
                    oos.close();
                }catch(Exception e){
                    Log.e(" ", "Error while closing stream "+e.getMessage());
                }
        }
    }

    public static List<Contact> read() {

        FileInputStream fin;
        ObjectInputStream ois=null;
        List<Contact> contact = null;
        if(contact ==null){
            contact = new ArrayList<>();
        }

        try{
            File apkStorage = new File(Environment.getExternalStorageDirectory() + "/pro");
            if (!apkStorage.exists()) {
                apkStorage.mkdir();
                Log.i("ll", "Directory Created.");
                Log.i("lllll", apkStorage.getPath());
            } else
                Log.i("", " directory already exist");
            File outputFile = new File(apkStorage, "phonebook.txt");
            if (!outputFile.exists()) {
                outputFile.createNewFile();
                Log.i("TAG", "File Created");
            }else
                Log.i("", "file already exist");

            fin= new FileInputStream(outputFile);

            //fin = new AppCompatActivity().getApplicationContext().openFileInput("myRecords.txt");
            ois = new ObjectInputStream(fin);
            List<Contact> records = (List<Contact>) ois.readObject();
            ois.close();
            Log.v(" ", "Records read successfully");
            return records;
        }catch(Exception e){
            Log.e(" ", "Cant read saved records"+e.getMessage());
            return null;
        }
        finally{
            if(ois!=null)
                try{
                    ois.close();
                }catch(Exception e){
                    Log.e(" ", "Error in closing stream while reading records"+e.getMessage());
                }
        }
    }

}