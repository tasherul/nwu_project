package com.example.time_parallel;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Show extends AppCompatActivity {

    public static String DataID;
    DatabaseHelper mDatabaseHelper;

    public void Show(String ID)
    {
        DataID  = ID;
    }
    static String Type_For_Goto_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        mDatabaseHelper = new DatabaseHelper(this);

        TextView Title,Discription,Week_or_Date,StartTime,EndTime,DayName;
        Title=(TextView)findViewById(R.id.show_txt2);
        Discription=(TextView)findViewById(R.id.show_txt10);
        Week_or_Date=(TextView)findViewById(R.id.show_txt4);
        StartTime=(TextView)findViewById(R.id.show_txt6);
        EndTime=(TextView)findViewById(R.id.show_txt8);
        DayName=(TextView)findViewById(R.id.textView3);

        Cursor data = mDatabaseHelper.getItemID(DataID);
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            String _ID =data.getString(0);
            String _Title =data.getString(1);
            String _Discription =data.getString(2);
            String _Weekly = data.getString(3);
            String _StartTime = data.getString(4);
            String _EndTime = data.getString(5);
            String _Type =data.getString(6);
            String _SDate =data.getString(7);

            Title.setText(_Title);
            Discription.setText(_Discription);
            StartTime.setText(_StartTime);
            EndTime.setText(_EndTime);

            if(_Type!="Schedule")
            {
                Week_or_Date.setText(_Weekly);
                DayName.setText("Date");
                Type_For_Goto_page=_Type;
            }
            else
            {
                Week_or_Date.setText(_SDate);
                DayName.setText("Day");
                Type_For_Goto_page=_Type;
            }

            break;
        }

        Button btnEdit,btnDelete;
        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnDelete=(Button)findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.DeleteData(DataID);
                MessageShow("This Schedule is Delete");


                if(Type_For_Goto_page!="Schedule")
                {
                    weekly _week = new weekly();
                   _week.weekly(Type_For_Goto_page);
                    OpenPageSchdule();
                }
                else
                {

                    weekly _week = new weekly();
                    _week.weekly(Type_For_Goto_page);
                    OpenPageSchdule();
                }

            }
        });





    }

    public void OpenPageSchdule()
    {
        Intent in = new Intent(this, weekly.class);//Weekly page
        startActivity(in);

    }

    public void MessageShow(String Massege)
    {
        Toast.makeText(this , Massege, Toast.LENGTH_SHORT).show();
    }
}
