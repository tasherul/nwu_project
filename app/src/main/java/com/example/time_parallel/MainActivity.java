package com.example.time_parallel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    btn = (Button) (Button) findViewById(R.id.btnOffline);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openLogin();
        }
    });

    }
    public void openLogin()
    {
        Intent in = new Intent(this,dashboard_offline.class);
        startActivity(in);
    }
}
