package com.damlakayali.notdefterim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    Button btnLogin,btnRegister;
    EditText etEmail,etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final DBHelper dbHelper = new DBHelper(getApplicationContext());

        SharedPreferences settings = getSharedPreferences("SQL", 0);
        boolean firstTime = settings.getBoolean("firstTime", true);

        if (firstTime) {
            //dbHelper.insertCountry(new Country("Turkiye", "90"));
            User u=new User();
            u.setEmail("damlakayali@gmail.com");
            u.setPassword("123456");
            u.setName("Damla");
            u.setSurname("Kayali");
            dbHelper.insertUser(u);
            Note n = new Note();
            n.setHeader("Deneme");
            n.setContent("icerikkk");
            n.setPriority(2);
            dbHelper.insertNotes(n);

            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstTime", false);
            editor.commit();
        }


        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        etEmail=(EditText)findViewById(R.id.loginEmail);
        etPass=(EditText)findViewById(R.id.loginPass);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean varMi=false;

                String email=etEmail.getText().toString();
                String pass=etPass.getText().toString();

                varMi=dbHelper.queryNote("Deneme");
                if(varMi==true){
                Toast.makeText(getApplicationContext(),"Bulundu",Toast.LENGTH_LONG).show();
                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);}
                else
                    Toast.makeText(getApplicationContext(),"Sanki bilgilerde bir yanlışlık var. :)",Toast.LENGTH_LONG).show();



            }
        });
    }
}
