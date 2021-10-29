package com.intent.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.intent.noteapp.modelsP.DatabaseSqliteMain;

public class MainActivity4 extends AppCompatActivity {

    EditText noteT , titleT;
    Button add;
    ImageView menuIcon;
    NavigationView naviV;
    DrawerLayout main_drawer_ly;
    private Menu main_menu ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        DatabaseSqliteMain databaseSqlite = new DatabaseSqliteMain(this);
        noteT= (EditText) findViewById(R.id.noteT);
        titleT= (EditText) findViewById(R.id.titleT);
        add= (Button) findViewById(R.id.addbtn);
        naviV= (NavigationView) findViewById(R.id.main_nav_view);
        main_drawer_ly=(DrawerLayout) findViewById(R.id.drawer_layout);
        main_menu=naviV.getMenu();
        menuIcon= (ImageView) findViewById(R.id.menu_ic);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_drawer_ly.openDrawer(GravityCompat.START);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!noteT.getText().toString().equals("")&&!titleT.getText().toString().equals(""))
                {

                    Toast.makeText(MainActivity4.this , databaseSqlite.insertDataNote(noteT.getText().toString(),titleT.getText().toString()) , Toast.LENGTH_LONG).show();
                    noteT.setText("");
                    titleT.setText("");

                }
                else Toast.makeText(MainActivity4.this,"Error , Fill all feilds" , Toast.LENGTH_LONG).show();

            }
        });
        naviV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                checkitemSelected(item);
                return false;
            }
        });
    }
    private void checkitemSelected(MenuItem item) {
        switch (item.getItemId()){
            case(R.id.call):
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:0779025502") );
                startActivity(intent);
                break;

            case (R.id.Main):
                Intent in = new Intent (MainActivity4.this, MainActivity3.class);

                startActivity(in);
                break;
            case (R.id.logout):
                Intent in1 = new Intent(MainActivity4.this, MainActivity.class);
                startActivity(in1);
                finish();

                break;



        } }
}