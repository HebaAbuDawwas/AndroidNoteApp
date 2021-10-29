package com.intent.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.intent.noteapp.modelsP.DatabaseSqliteMain;
import com.intent.noteapp.modelsP.Notes;
import com.intent.noteapp.modelsP.User;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    NavigationView naviV;
    DrawerLayout main_drawer_ly;
    private Menu main_menu ;
    ImageView menuIcon;
    TextView texttitle;
    TextView textid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


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
        DatabaseSqliteMain databaseSqlite = new DatabaseSqliteMain(this);
        ArrayList<Notes> arrayList = databaseSqlite.getDataNote();




        //-------------------------------------------------------
        ListView listView = (ListView) findViewById(R.id.listviewD);
        Add a = new Add(arrayList);
        listView.setAdapter(a);

        //--------------------------------------------------------
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                databaseSqlite.DeleteNote(arrayList.get(position).getId()+"");

                return false;
            }
        }) ;
        //--------------------------------------------------------
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity3.this,""+arrayList.get(position).getNotetitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity3.this, NoteActivity.class);
                intent.putExtra("noteTitle",arrayList.get(position).getNotetitle());
                intent.putExtra("noteText",arrayList.get(position).getNote());
                intent.putExtra("id",arrayList.get(position).getId());


                startActivity(intent);


            }
        });


        //----------------
        //--------------------------------------------------------
        naviV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                checkitemSelected(item);
                return false;
            }
        });
        //-------------


    }
    class Add extends BaseAdapter {

        ArrayList<Notes> l = new ArrayList<Notes>();

        public Add(ArrayList<Notes> v) {
            this.l = v;
        }

        @Override
        public int getCount() {
            return l.size();
        }

        @Override
        public Object getItem(int position) {
            return l.get(position).toString();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater ly = getLayoutInflater();
            View view = ly.inflate(R.layout.menu_item, null);

            texttitle = (TextView) view.findViewById(R.id.notetitle);
            textid = (TextView)  view.findViewById(R.id.noteid);
            texttitle.setText(String.valueOf(l.get(position).getNotetitle()));
            textid.setText(String.valueOf(l.get(position).getId()));



            return view;
        }
    }

    private void checkitemSelected(MenuItem item) {
        switch (item.getItemId()){
            case(R.id.call):
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:0779025502") );
                startActivity(intent);
                break;
            case (R.id.Add_note):
                Intent in = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(in);

                break;
            case (R.id.logout):
                Intent in1 = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(in1);
                finish();

                break;




        } }

}