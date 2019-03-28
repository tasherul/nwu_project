package com.example.time_parallel;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class Main_add_exam extends AppCompatActivity
        implements TimePickerDialog.OnTimeSetListener{
public static String Show;
    Button btnStartTime,btnEndTime,btnBack,btnSave;
    boolean B_StartTime  = false,B_EndTime =false ;
    DatabaseHelper mDatabaseHelper;
    public void Main_add_exam(String ID)
    {
     Show=ID;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_exam);

        btnStartTime = (Button)findViewById(R.id._btnStartTime);
        btnEndTime = (Button)findViewById(R.id._btnEndTime);
        btnSave = (Button)findViewById(R.id._btnSave);
        btnBack  = (Button)findViewById(R.id._btnBack);
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoDashboard();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });
    }

    private void SaveData()
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

            boolean insertData = mDatabaseHelper.addData(S_Title,S_Discription,S_ddlDay,S_StartTime,S_EndTime,Show,"");
            if(insertData){
                toastMessage("Successfully  "+Show+ " Add.");
                StartTime.setText("");
                EndTime.setText("");
                Title.setText("");
                Discription.setText("");
            }
            else
                toastMessage("Error Data Can't Add.");
        }
        else
        {
            toastMessage( "Please Type Schedule Details.");
        }


    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public void GotoDashboard()
    {
        Intent in = new Intent(this, dashboard_offline.class);
        startActivity(in);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(B_StartTime)
        {
            TextView textView = (TextView)findViewById(R.id.txtStartTime );
            textView.setText(hourOfDay+" : "+minute);
        }

        if(B_EndTime)
        {
            TextView textView = (TextView)findViewById(R.id.end_time_add   );
            textView.setText(hourOfDay+" : "+minute);
        }
    }
}
