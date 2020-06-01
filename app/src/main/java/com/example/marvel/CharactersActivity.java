package com.example.marvel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.marvel.json.JsonMarvelModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CharactersActivity extends AppCompatActivity {
    JsonMarvelModel model;
    TextView playername;
    private RecyclerView charRecyclerView;
    private RecyclerView.Adapter charAdapter;
    private RecyclerView.LayoutManager charLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        playername=findViewById(R.id.PlayerName);
        String nickname=getIntent().getStringExtra("nickname");
        playername.setText(nickname.toString());
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url2 ="http://gateway.marvel.com/v1/public/characters?ts=1590763605&apikey=85945ebf049310af9b57bdd9b21361ac&hash=2371fd05d862c167376b22e74613e634";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                model = new Gson().fromJson(response, JsonMarvelModel.class);
                Log.i("RESPONSE", model.toString());
                //model.getData().getResults().get(1)
                //TextView t = (TextView)findViewById(R.id.textView5);
                //t.setText(model.getData().getResults().get(1).getName());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(stringRequest);
        filllist();


    }

    public void filllist(){
        int countlist= model.getData().getResults().size();
        ArrayList<ListItem> charsList = new ArrayList<>();
        for (int i=0;i<countlist;i++) {
            String iconpath;
            String charname;
            charname = model.getData().getResults().get(i).getName();
            iconpath = model.getData().getResults().get(i).getThumbnail().getPath() + "."+ model.getData().getResults().get(i).getThumbnail().getExtension();
            charsList.add(new ListItem(iconpath,R.drawable.eyeicon,charname));
        }
        charRecyclerView = findViewById(R.id.recyclerview);
        charRecyclerView.setHasFixedSize(true);
        charLayoutManager = new LinearLayoutManager(this);
        charAdapter = new CharAdapter(charsList);
        charRecyclerView.setLayoutManager(charLayoutManager);
        charRecyclerView.setAdapter(charAdapter);


    }




}
