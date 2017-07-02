package com.example.enchanter21.socialdating;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.widget.Toast.makeText;

public class RegisterActivity extends AppCompatActivity {

    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.cv_add)
    CardView cvAdd;
    EditText ename,epass1,ephn,egend,eage,edets;

    String sname,spass,sphn,sgend,sdets,sage;

    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);

        bt1=(Button)findViewById(R.id.bt_go);
        ename=(EditText)findViewById(R.id.et_username);
        epass1=(EditText)findViewById(R.id.et_password);
        ephn=(EditText)findViewById(R.id.et_mobile);
        egend=(EditText)findViewById(R.id.et_gender);
        eage=(EditText)findViewById(R.id.et_age);
        edets=(EditText)findViewById(R.id.et_details);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ShowEnterAnimation();
                }
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        animateRevealClose();
                    }
                });

                sname=ename.getText().toString();
                spass=epass1.getText().toString();
//                epass1.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                sphn= ephn.getText().toString();
                sgend=egend.getText().toString();
                sage=eage.getText().toString();
                sdets=edets.getText().toString();

                if(sname.isEmpty()||spass.isEmpty()||sphn.isEmpty()||sgend.isEmpty()||sage.isEmpty()||sdets.isEmpty())
                {
                    makeText(getApplicationContext(),"No field should be empty", Toast.LENGTH_SHORT).show();
                }
                else if(sphn.length()<10||sphn.length()>10)
                {
                    makeText(getApplicationContext(),"Phone number should contain 10 characters",Toast.LENGTH_SHORT).show();
                }
                else {
                    insert_service(sname,spass,sphn,sgend,sage,sdets);
                }}

        });


    }
        private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    private void insert_service(final String ssname, final String sspass, final String ssphn,final String ssgend,final String ssage,final String ssdets) {
        StringRequest stringreqs=new StringRequest(Request.Method.POST, Global_Url.URI_CATEGORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean abc = jObj.getBoolean("exits");
                    if (abc)
                    {
                        JSONObject users = jObj.getJSONObject("user_det");
                        String nameds1 = users.getString("nameds2");
                        String passds1 = users.getString("passds2");
                        String uuid1 = users.getString("uuid2");
                        String phno1=users.getString("phno2");
                        String gend1=users.getString("gend2");
                        String detas1=users.getString("detas2");
                        String age1=users.getString("age2");
                        Intent intent1=new Intent(RegisterActivity.this,Homenav.class);

//                        intent1.putExtra("nameds3",nameds1);
//                        intent1.putExtra("passds3",passds1);
//                        intent1.putExtra("uuid3",uuid1);
//                        intent1.putExtra("phno3",phno1);
//                        intent1.putExtra("gend3",gend1);
//                        intent1.putExtra("age3",age1);
//                        intent1.putExtra("detas3",detas1);
                        startActivity(intent1);
//                        makeText(getApplicationContext(),"Welcome\t\t"+nameds1,Toast.LENGTH_SHORT).show();
                        //JSONObject users = jObj.getJSONObject("user_det");

//                        Intent intent=new Intent(MainActivity.this,Home_Screen.class);
//                        startActivity(intent);
//                        makeText(getApplicationContext(),"User Exists already",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Server Busy",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                makeText(getApplicationContext(),"INTERNET CONNECTION NOT AVAILABLE",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> uandme=new HashMap<String, String>();
                uandme.put("ur_name",ssname);
                uandme.put("ur_pwwd",sspass);
                uandme.put("ur_mobno",ssphn);
                uandme.put("ur_gender",ssgend);
                uandme.put("ur_age",ssage);
                uandme.put("other_details",ssdets);
                return uandme;
            }
        };
        App_Controller.getInstance().addToRequestQueue(stringreqs);
    }
}
