package com.intent.noteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
   int userId = MainActivity.userId;
   AlertDialog.Builder alertDelete;
    AlertDialog.Builder alertSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        databaseSqlite = new DatabaseSqliteMain(this);
        arrayList = databaseSqlite.getDataNote(userId);

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

                 alertSave= new AlertDialog.Builder(NoteActivity.this);
                    alertSave.setTitle("SURE ?");
                    alertSave.setMessage("Are You Sure You Want To Save Edits :\nnote title:"+noteTitle+"\nnote:"+temp+"   ?");
                    alertSave.setPositiveButton("Conform", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                             Toast.makeText(NoteActivity.this,temp, Toast.LENGTH_SHORT).show();
                               databaseSqlite.UpdateNote(Id+"",temp,noteTitle);
                            Intent in = new Intent(NoteActivity.this,MainActivity3.class);
                            startActivity(in);
                            finish();

                        }
                    });
                    alertSave.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent in = new Intent(NoteActivity.this,MainActivity3.class);
                            startActivity(in);
                            finish();
                        }
                    });
                    alertSave.show();





            }

        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    alertDelete= new AlertDialog.Builder(NoteActivity.this);
                    alertDelete.setTitle("SURE ?");
                    alertDelete.setMessage("Are You Sure You Want To delete This Note :\nnote title:"+noteTitle+"\nnote:"+noteText+"   ?");
                    alertDelete.setPositiveButton("Conform", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseSqlite.DeleteNote(Id+"");
                            Intent in = new Intent(NoteActivity.this,MainActivity3.class);
                            startActivity(in);
                            finish();
                        }
                    });
                    alertDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDelete.show();



            }

        });









    }
}