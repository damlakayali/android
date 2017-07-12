package com.damlakayali.notdefterim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class UpdateNote extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner priority;
    EditText header,content;
    Button btnUpdate;
    Note n=new Note();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

      Intent i=getIntent();
        final Note note=(Note)i.getSerializableExtra("giden");
        final int id=note.getId();

        priority=(Spinner)findViewById(R.id.updadteSpinner);
        header=(EditText)findViewById(R.id.update_header);
        content=(EditText)findViewById(R.id.update_content);
        btnUpdate=(Button)findViewById(R.id.btnUpdate2);

        final DBHelper dbHelper = new DBHelper(getApplicationContext());
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
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n.setContent(content.getText().toString());
                n.setHeader(header.getText().toString());


                dbHelper.NoteUpdata(id,n.getHeader(),n.getContent(),n.getPriority());
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
