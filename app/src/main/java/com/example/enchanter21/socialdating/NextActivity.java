package com.example.enchanter21.socialdating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {

    TextView tw1,tw2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        tw1=(TextView)findViewById(R.id.txtname);
        tw2=(TextView)findViewById(R.id.txtdetails);
        String ab=getIntent().getStringExtra("abc");
        String abb=getIntent().getStringExtra("acb");
        tw1.setText(ab);
        tw2.setText(abb);
    }
}
