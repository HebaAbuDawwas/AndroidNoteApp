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
    public static int userId;
    ArrayList<User> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Toast.makeText(MainActivity.this,"Test", Toast.LENGTH_SHORT).show();
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
        arrayList=null;

        arrayList=databaseSqlite.getDataUser();

        //for(int i=0;i<arrayList.size();i++)
          //  Toast.makeText(MainActivity.this,arrayList.get(i).getEmail(),Toast.LENGTH_SHORT).show();




        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=false;
                // Toast.makeText(MainActivity.this,arrayList.size()+"",Toast.LENGTH_SHORT).show();
                //for(int i=0;i<arrayList.size();i++)
                  //  Toast.makeText(MainActivity.this,arrayList.get(i).getEmail(),Toast.LENGTH_SHORT).show();
                for(int i=0;i<arrayList.size();i++){
                    if(arrayList.get(i).getEmail().equals(email.getText().toString()) && arrayList.get(i).getPassword().equals(passWord.getText().toString()))
                    {
                        flag=true;
                        userId= arrayList.get(i).getId();

                        break;
                    }





                }

                if(flag) {
                    email.setText("");
                    passWord.setText("");

                    Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                    startActivity(intent);

                }
                else {
                    email.setText("");
                    passWord.setText("");
                    Toast.makeText(MainActivity.this,"Invalid Email or Password" , Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}