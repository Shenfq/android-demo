package com.example.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    public String TAG = "log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Intent parent = getIntent();
        String data = parent.getStringExtra("extra_data");
        Log.d(TAG, "onCreate: " + data);

        Button button = (Button) findViewById(R.id.go_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("data_return", "Hello MainActivity");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
