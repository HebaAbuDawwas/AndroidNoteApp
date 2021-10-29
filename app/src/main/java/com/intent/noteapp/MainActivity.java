package com.intent.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.intent.noteapp.modelsP.DatabaseSqliteMain;
import com.intent.noteapp.modelsP.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText passWord,email;
    Button signUp;
    Button signin;
    DatabaseSqliteMain databaseSqlite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseSqlite = new DatabaseSqliteMain(this);

        email = (EditText) findViewById(R.id.email);
        passWord = (EditText) findViewById(R.id.password);
        signUp = (Button) findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.login) ;



        //--------------------------------
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        //-----------------------------
        ArrayList<User> arrayList=databaseSqlite.getDataUser();




        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=false;


                for(int i=0;i<arrayList.size();i++){
                    if(arrayList.get(i).getEmail().equals(email.getText().toString()) && arrayList.get(i).getPassword().equals(passWord.getText().toString()))
                    {   flag=true;

                        break;
                    }

                    email.setText("");
                    passWord.setText("");



                }

                if(flag) {

                    Intent intent = new Intent(MainActivity.this,MainActivity3.class);

                    startActivity(intent);

                }
                else {
                    Toast.makeText(MainActivity.this,"Invalid Email or Password" , Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}