package com.example.enchanter21.socialdating;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Likedlist extends AppCompatActivity {

    ListView listv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likedlist);

        listv1=(ListView)findViewById(R.id.list12);
            new kilomilo().execute(Global_Url.URI_CATEGORY2);
        }

        public class MovieAdap extends ArrayAdapter {
            private List<frndslist> movieModelList;
            private int resource;
            Context context;
            private LayoutInflater inflater;
            MovieAdap(Context context, int resource, List<frndslist> objects) {
                super(context, resource, objects);
                movieModelList = objects;
                this.context = context;
                this.resource = resource;
                inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            }
            @Override
            public int getViewTypeCount() {
                return 1;
            }
            @Override
            public int getItemViewType(int position) {
                return position;
            }
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                final ViewHolder holder;
                if (convertView == null) {
                    convertView = inflater.inflate(resource, null);
                    holder = new ViewHolder();
                    holder.txnm=(TextView)  convertView.findViewById(R.id.txtnm);
                    holder.txdt = (TextView) convertView.findViewById(R.id.txtdet);
                    convertView.setTag(holder);
                }
                else {
                    holder = (ViewHolder) convertView.getTag();
                }
                frndslist ccitac = movieModelList.get(position);
                holder.txnm.setText("Name:"+ccitac.getUr_name());
                holder.txdt.setText("Details:"+ccitac.getOther_details());
                //Glide.with(context).load(ccitac.getBus_image()).into(holder.bus_image);
                return convertView;
            }
            class ViewHolder {
                public TextView txnm;
                public TextView txdt;

                //public ImageView bus_image;

            }
        }
        public class kilomilo extends AsyncTask<String, String, List<frndslist>> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected List<frndslist> doInBackground(String... params) {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(params[0]);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream stream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder buffer = new StringBuilder();
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    String finalJson = buffer.toString();
                    JSONObject parentObject = new JSONObject(finalJson);
                    JSONArray parentArray = parentObject.getJSONArray("result");
                    List<frndslist> milokilo = new ArrayList<>();
                    Gson gson = new Gson();
                    for (int i = 0; i < parentArray.length(); i++) {
                        JSONObject finalObject = parentArray.getJSONObject(i);
                        frndslist catego = gson.fromJson(finalObject.toString(), frndslist.class);
                        milokilo.add(catego);
                    }
                    return milokilo;
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
            @Override
            protected void onPostExecute(final List<frndslist> movieMode) {
                super.onPostExecute(movieMode);
                if (movieMode.size() > 0) {
                    MovieAdap adapter = new MovieAdap(getApplicationContext(), R.layout.frndslists, movieMode);
                    listv1.setAdapter(adapter);
                    listv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            frndslist item = movieMode.get(position);
                            ViewGroup vb=(ViewGroup) view;
                            //Intent intent = new Intent(Friendslist.this,NextActivity.class);
//                            TextView t1=(TextView)vb.findViewById(R.id.txtnm);
//                            TextView t2=(TextView)vb.findViewById(R.id.txtdet);
//
//                            String amm=t1.getText().toString();
//                            String ann=t2.getText().toString();
//                            Intent in=new Intent(Likedlist.this,NextActivity.class);
//                            in.putExtra("abc",amm);
//                            in.putExtra("acb",ann);
//
//                            startActivity(in);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

