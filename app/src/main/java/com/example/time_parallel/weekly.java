package com.example.time_parallel;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;



public class weekly extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
       static  String _Schedule;
    public void weekly(String Schedule)
    {
        _Schedule=Schedule;
    }
    private ListView lvProduct;
    private ScheduleListAdapter adapter;
    private List<Schedul> mProductList;

    DatabaseHelper mDatabaseHelper;
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        mDatabaseHelper = new DatabaseHelper(this);

        mListView = (ListView) findViewById(R.id.listview_product );
        //ShowGridViewData();

        ShowProductData();


        //-----------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void ShowProductData() {
        lvProduct = (ListView)findViewById(R.id.listview_product);
        mProductList = new ArrayList<>();
        Cursor data = mDatabaseHelper.getDataByType(_Schedule);

        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            String ID =data.getString(0);
            String Title =data.getString(1);
            String Discription =data.getString(2);
            String Weekly = data.getString(3);
            String StartTime = data.getString(4);
            String EndTime = data.getString(5);
            String Type =data.getString(6);
            String SDate =data.getString(7);
            mProductList.add(new Schedul(ID,Title,Discription,Weekly,StartTime,EndTime,Type,SDate));
        }
        adapter = new ScheduleListAdapter(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                String ID  = view.getTag().toString();
                Show SW = new Show();
                SW.Show(ID);
                OpenShowFile();

            }
        });
    }

    public void OpenShowFile()
    {
        Intent in = new Intent(this, Show.class);//Weekly page
        startActivity(in);

    }
    private void ShowGridViewData() {
        Cursor data = mDatabaseHelper.getDataByType(_Schedule);
        //Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        ArrayList<Integer> IDs = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            String Title =data.getString(1);
            String Day = data.getString(3);
            String StartTime = data.getString(4);
            String EndTime = data.getString(5);
            String ID =data.getString(0);
            listData.add( "Title : "+  Title + "\n"+"Day : "+Day+"\nTime : "+StartTime+" to "+EndTime+"\n  #   "+ID );
            IDs.add(Integer.parseInt(ID));
        }

        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1      , listData    );
        mListView.setAdapter(adapter);
        mListView.setTag(IDs);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();

                //MessageShow(parent.getTag(position).toString() );
            }
        });


    }

    public void MessageShow(String Massege)
    {
        Toast.makeText(this , Massege, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard_offline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent in = new Intent(this, dashboard_offline.class );
            startActivity(in);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {


            Intent in = new Intent(this, Main_add.class);//Weekly page
            startActivity(in);


        } else if (id == R.id.nav_slideshow) {
            Intent in = new Intent(this,  Main_add_exam.class );
            Main_add_exam  m = new Main_add_exam();
            m.Main_add_exam("Exam");
            startActivity(in);
        } else if (id == R.id.nav_manage) {
            Intent in = new Intent(this, Main_add_exam.class );
            Main_add_exam  m = new Main_add_exam();
            m.Main_add_exam("Task");
            startActivity(in);
        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
