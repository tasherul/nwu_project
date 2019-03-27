package com.example.time_parallel;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class dashboard_offline extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TimePickerDialog.OnTimeSetListener
        {

            DatabaseHelper mDatabaseHelper;
            ListView mListView;
            private static final String TAG = "ListDataActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_offline);
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

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // DialogFragment timePicker = new TimePickerFragment();
               // timePicker.show( getSupportFragmentManager(), "time picker") ;

                sama();

            }
        });

        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();

    }

    private void populateListView() {
                 Log.d(TAG, "populateListView: Displaying data in the ListView.");

                //get the data and append to a list
                Cursor data = mDatabaseHelper.getData();
                ArrayList<String> listData = new ArrayList<>();
                
                while(data.moveToNext()){
                    //get the value from the database in column 1
                    //then add it to the ArrayList
                    listData.add(data.getString(1));

                }
                //create the list adapter and set the adapter
                ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1    , listData);
                mListView.setAdapter(adapter);



               /*
                //set an onItemClickListener to the ListView
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String name = adapterView.getItemAtPosition(i).toString();
                        Log.d(TAG, "onItemClick: You Clicked on " + name);

                        Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                        int itemID = -1;
                        while(data.moveToNext()){
                            itemID = data.getInt(0);
                        }
                        if(itemID > -1){
                           // Log.d(TAG, "onItemClick: The ID is: " + itemID);
                           // Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                           // editScreenIntent.putExtra("id",itemID);
                            //editScreenIntent.putExtra("name",name);
                            //startActivity(editScreenIntent);
                        }
                        else{
                            toastMessage("No ID associated with that name");
                        }
                    }
                });
               * */
            }
    public void sama()
    {
        Intent in = new Intent(this, Main_add.class);
        startActivity(in);
    }

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText("H: " + hourOfDay +"M: " + minute);
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
            Intent in = new Intent(this, dashboard_offline.class);
            startActivity(in);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {


                    Intent in = new Intent(this, weekly.class);
                    startActivity(in);


        } else if (id == R.id.nav_slideshow) {
            Intent in = new Intent(this, exam_ofline.class);
            startActivity(in);

        } else if (id == R.id.nav_manage) {
            Intent in = new Intent(this, Task.class);
            startActivity(in);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void MessageShow(String Massege)
    {
        Toast.makeText(this , Massege, Toast.LENGTH_SHORT).show();
    }
            private void toastMessage(String message){
                Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
            }

}
