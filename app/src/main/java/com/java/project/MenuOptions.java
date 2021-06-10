package com.java.project;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuOptions extends AppCompatActivity {

    String newString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING_I_NEED");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_options);
        Contact c = Manager.getdetailsByName(newString);

        TextView t = findViewById(R.id.name);
        t.setText(c.getName());
        TextView t2 = findViewById(R.id.viewnumber);
        t2.setText(c.getMobile());
        TextView t3 = findViewById(R.id.viewemail);
        t3.setText(c.getEmail());
    }

    public void delete(View view){
        AlertDialog dBox = Delete(view);
        dBox.show();
    }
    public void update(View view){
        TextView tv1 = findViewById(R.id.viewnumber);
        String oldnumber = (String) tv1.getText();
        Intent i = new Intent(view.getContext(), AddNewContact.class);
        i.putExtra("STRING_I_NEED", oldnumber);
        startActivity(i);
    }
    public void call(View view){
        Toast.makeText(getApplicationContext(), "calling...", Toast.LENGTH_LONG).show();
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        TextView t2 = findViewById(R.id.viewnumber);
        phoneIntent.setData(Uri.parse("tel:" + t2.getText().toString()));
        startActivity(phoneIntent);
    }


    //delete confirmation msg box
    private AlertDialog Delete(View view)
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                .setTitle("Delete").setMessage("Do you want to Delete").setPositiveButton("Delete", (dialog, whichButton) -> {
                    Toast.makeText(getApplicationContext(), "delete...", Toast.LENGTH_LONG).show();
                    TextView tv1 = findViewById(R.id.viewnumber);
                    Manager.delete((String) tv1.getText());
                    Intent myIntent = new Intent(view.getContext(), ViewContact.class);
                    startActivity(myIntent);
                    dialog.dismiss();
                })
                .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss())
                .create();
        return myQuittingDialogBox;
    }
}