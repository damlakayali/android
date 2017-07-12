package com.damlakayali.notdefterim;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NoteInsert extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText headerET;
    EditText contentET;
    Spinner priority;
    Button btnInsert;
    Note n=new Note();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_insert);

        final DBHelper dbHelper = new DBHelper(getApplicationContext());

        headerET=(EditText)findViewById(R.id.baslik);
        contentET=(EditText)findViewById(R.id.icerik);
        priority=(Spinner)findViewById(R.id.prioritySpinner);
        btnInsert=(Button)findViewById(R.id.btnInsert);

        priority.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String>  categories = new ArrayList<String>();
        categories.add("Çok Önemli");
        categories.add("Önemli");
        categories.add("Normal");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        priority.setAdapter(dataAdapter);


                /* ALERT DIOLOG START */
        final AlertDialog.Builder alertbox2 = new AlertDialog.Builder(this);
        alertbox2.setTitle("BİLGİ");
        alertbox2.setMessage("Notunuz başarıyla eklendi. :)");

        alertbox2.setNegativeButton("Not Listesine Git",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }
                });
        alertbox2.setPositiveButton("Yeni Not Ekle",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                       Intent i= new Intent(getApplicationContext(),NoteInsert.class);
                        startActivity(i);
                    }
                });

        // ALERT DIOLOG FINISH
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n.setHeader(headerET.getText().toString());
                n.setContent(contentET.getText().toString());
                dbHelper.insertNotes(n);
                alertbox2.show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        if(item.equals("Çok Önemli")){
            n.setPriority(1);
        }
        if (item.equals("Önemli")){
            n.setPriority(2);
        }
        if(item.equals("Normal")){
            n.setPriority(3);
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
