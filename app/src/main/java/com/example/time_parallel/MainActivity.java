package com.example.time_parallel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button btn;
    private int notificationId = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 /*   btn = (Button) findViewById(R.id.btnOffline);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //openLogin();

        }
    });  */

        findViewById(R.id.btnOffline).setOnClickListener(this);
        //findViewById(R.id.cancelBtn).setOnClickListener(this);
    }
    public void openLogin()
    {
        Intent in = new Intent(this,dashboard_offline.class);
        startActivity(in);
    }

    @Override
    public void onClick(View view) {
        EditText editText = findViewById(R.id.editText);




        // getBroadcast(context, requestCode, intent, flags)





Button btn = (Button)findViewById(R.id.btnOffline);
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //TimePicker timePickers = findViewById(R.id.timePicker);
    /*    int hour =0;//  timePickers.getCurrentHour();
        int minute =0;// timePickers.getCurrentMinute();

        // Create time.
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, hour);
        startTime.set(Calendar.MINUTE, minute);
        startTime.set(Calendar.SECOND, 0);
        long alarmStartTime = startTime.getTimeInMillis();

        // Set notificationId & text.
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("todo", "Hallow World");
        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        // Set alarm.
        // set(type, milliseconds, intent)
        alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
*/
        //Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
          openLogin();
    }

});






    }
}
