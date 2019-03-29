package com.example.time_parallel;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class edit extends AppCompatActivity
        implements TimePickerDialog.OnTimeSetListener{

    public static String ID_Edit,Type_Edit;
    public void edit(String ID,String Type){ID_Edit=ID;Type_Edit=Type;}
    Button btnUpdate,btnDashboard,btnStartTime,btnEndTime;
    boolean B_StartTime  = false,B_EndTime =false ;
    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mDatabaseHelper =new DatabaseHelper(this );


        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData();
            }
        });



        EditText StartTime,EndTime,Title,Discription,Date,Type;

        StartTime = (EditText)findViewById(R.id.txtStartTime);
        EndTime = (EditText)findViewById(R.id.end_time_add);
        Title = (EditText)findViewById(R.id.txtTitle);
        Discription = (EditText)findViewById(R.id.txtDiscription);
        Date = (EditText)findViewById(R.id.txtDate);
        //Type= (EditText)findViewById(R.id.txt);

        Cursor data = mDatabaseHelper.getItemID(ID_Edit)  ;
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            String ID =data.getString(0);
             Title.setText ( data.getString(1) );
              Discription.setText ( data.getString(2));
            String Weekly = data.getString(3);
              StartTime .setText ( data.getString(4));
              EndTime .setText ( data.getString(5));
           // String Type =data.getString(6);
            String D = data.getString(7);
            String W = data.getString(3);
            if(D!=W)
            {
                Date .setText(D+W);

            }
            else
            {

                Date .setText(D);
            }

        }


        btnDashboard =(Button)findViewById(R.id.btnDashboard);
        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboard();
            }
        });
        btnStartTime = (Button)findViewById(R.id._btnStartTime);
        btnEndTime = (Button)findViewById(R.id._btnEndTime);
        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show( getSupportFragmentManager(), "Start Time") ;
                B_StartTime =true;
                B_EndTime  = false;
            }
        });
        mDatabaseHelper  = new DatabaseHelper(this);
        btnEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show( getSupportFragmentManager(), "End Time") ;
                B_StartTime =false;
                B_EndTime  = true;
            }
        });
    }
    private void UpdateData()
    {
        EditText StartTime,EndTime,Title,Discription,Date;

        StartTime = (EditText)findViewById(R.id.txtStartTime);
        EndTime = (EditText)findViewById(R.id.end_time_add);
        Title = (EditText)findViewById(R.id.txtTitle);
        Discription = (EditText)findViewById(R.id.txtDiscription);
        Date = (EditText)findViewById(R.id.txtDate);

        String S_Title  = Title.getText().toString();
        String S_Discription  = Discription.getText().toString();
        String S_StartTime = StartTime.getText().toString();
        String S_EndTime  = EndTime.getText().toString();
        String S_ddlDay = Date.getText().toString();

        if(S_Title.length()!=0 && S_Discription.length()!=0 && S_StartTime.length()!=0 && S_EndTime.length()!=0 && S_ddlDay.length()!=0 )
        {
            //toastMessage(S_ddlDay);

             mDatabaseHelper.UpdateData(ID_Edit,String.format( " Title='%s', Discription='%s', StartTime='%s', Endtime='%s', SDate='%s', Weekly='%s'  ",S_Title,S_Discription,S_StartTime,S_EndTime,S_ddlDay,S_ddlDay ));

                toastMessage("Successfully Update Data");

        }
        else
        {
            toastMessage( "Please Type Schedule Details.");
        }


    }
    public void openDashboard()
    {
        Intent in = new Intent(this, dashboard_offline.class );
        startActivity(in);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(B_StartTime)
        {
            TextView textView = (TextView)findViewById(R.id.txtStartTime );
            textView.setText(hourOfDay+":"+minute);
        }

        if(B_EndTime)
        {
            TextView textView = (TextView)findViewById(R.id.end_time_add   );
            textView.setText(hourOfDay+":"+minute);
        }
    }
}
