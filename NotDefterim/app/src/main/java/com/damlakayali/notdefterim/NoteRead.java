package com.damlakayali.notdefterim;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NoteRead extends AppCompatActivity {

    TextView headerText,contentText;
    Button btnDelete,btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_read);

        final DBHelper dbHelper = new DBHelper(getApplicationContext());

        headerText=(TextView)findViewById(R.id.read_header);
        contentText=(TextView)findViewById(R.id.read_content);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);

        Intent i = getIntent();
        final Note n=(Note)i.getSerializableExtra("SecilenNot");
        final int id=n.getId();

        headerText.setText(n.getHeader().toString());
        contentText.setText(n.getContent().toString());

        final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle("DİKKAT");
        alertbox.setMessage("Seçilen not silinecek. Emin misin? :)");

        alertbox.setNeutralButton("Eminim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        dbHelper.NoteDelete(id);
                    }
                });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertbox.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),UpdateNote.class);
                i.putExtra("giden",n);
                startActivity(i);
            }
        });
    }
}
