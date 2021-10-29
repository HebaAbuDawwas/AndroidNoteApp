package com.intent.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.intent.noteapp.modelsP.DatabaseSqliteMain;

public class MainActivity2 extends AppCompatActivity {
    EditText fullname , username , email, password ;
    Button register;
    DatabaseSqliteMain databaseSqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Sign Up");
        databaseSqlite = new DatabaseSqliteMain(this);
        fullname = (EditText) findViewById(R.id.fullname);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email1);
        password = (EditText) findViewById(R.id.password1);
        register = (Button) findViewById(R.id.register);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fullname.getText().toString().equals("")&&!username.getText().toString().equals("")&&!email.getText().toString().equals("")&&!password.getText().toString().equals(""))
                {

                    Toast.makeText(MainActivity2.this ,databaseSqlite.insertDataUser(fullname.getText().toString(),username.getText().toString(), email.getText().toString() ,password.getText().toString()), Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                }
                else Toast.makeText(MainActivity2.this,"Error , Fill all feilds" , Toast.LENGTH_LONG).show();


            }
        });
    }
}