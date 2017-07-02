package com.example.enchanter21.socialdating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;

/**
 * Created by enchanter21 on 30/6/17.
 */

public class LoginSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_success);

        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
    }
}
