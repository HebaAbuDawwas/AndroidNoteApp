package com.intent.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.intent.noteapp.modelsP.DatabaseSqliteMain;
import com.intent.noteapp.modelsP.Notes;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {
    EditText noteEditText;
    ImageView save,delete;
    ArrayList<Notes> arrayList;
    DatabaseSqliteMain databaseSqlite;
    TextView textbar;
    int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        databaseSqlite = new DatabaseSqliteMain(this);
        arrayList = databaseSqlite.getDataNote();

        Intent intent = getIntent();
        String noteTitle = intent.getExtras().getString("noteTitle");
        String noteText = intent.getExtras().getString("noteText");
        Id = intent.getExtras().getInt("id");
        noteEditText = (EditText) findViewById(R.id.noteEditText);
        save = (ImageView) findViewById(R.id.save_ic);
        delete = (ImageView)findViewById(R.id.delete_ic);
        noteEditText.setText(noteText);
        textbar =(TextView) findViewById(R.id.textbar);
        textbar.setText(noteTitle);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp;
                temp =  noteEditText.getText().toString();

                Toast.makeText(NoteActivity.this,temp, Toast.LENGTH_SHORT).show();
                databaseSqlite.UpdateNote(Id+"",temp,noteTitle);
                Intent in = new Intent(NoteActivity.this,MainActivity3.class);
                startActivity(in);
            }

        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseSqlite.DeleteNote(Id+"");
                Intent in = new Intent(NoteActivity.this,MainActivity3.class);
                startActivity(in);
            }

        });









    }
}