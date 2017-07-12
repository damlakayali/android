package com.damlakayali.notdefterim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView lw;
    Button btnInsert;

    Note note = new Note();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lw=(ListView)findViewById(R.id.list);
        btnInsert=(Button)findViewById(R.id.btnInsertNote);

        final DBHelper dbHelper = new DBHelper(getApplicationContext());

        SharedPreferences settings = getSharedPreferences("SQL", 0);
        boolean firstTime = settings.getBoolean("firstTime", true);

        if (firstTime) {

            Note n = new Note();
            n.setHeader("Deneme");
            n.setContent("icerikkk");
            n.setPriority(2);
            dbHelper.insertNotes(n);

            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstTime", false);
            editor.commit();
        }


     List<Note> notes = dbHelper.getAllNotes();
        MyListAdapter myListAdapter= new MyListAdapter(MainActivity.this,notes);
        lw.setAdapter(myListAdapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),NoteInsert.class);
                startActivity(i);
            }
        });

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                note = dbHelper.getNote(position);
                Intent i= new Intent(getApplicationContext(),NoteRead.class);
                i.putExtra("SecilenNot",note);
                startActivity(i);

            }
        });

    }
}
