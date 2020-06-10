package com.example.marvel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.marvel.json.JsonMarvelModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CharactersActivity extends AppCompatActivity  {
    JsonMarvelModel model;
    TextView playername;
    SearchView searchbar;
    private RecyclerView charRecyclerView;
    private CharAdapter charAdapter;
    private RecyclerView.LayoutManager charLayoutManager;
    int imageresource=0;
    FirebaseDatabase firebase;
    DatabaseReference reference;
    String nickname;
    ArrayList<ListItem> charsList;
    List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        playername=findViewById(R.id.PlayerName);
        nickname=getIntent().getStringExtra("nickname");
        playername.setText(nickname);
        charsList = new ArrayList<>();
        searchbar = findViewById(R.id.SearchView);
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                charAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        charRecyclerView = findViewById(R.id.recycleview);
        charRecyclerView.setHasFixedSize(true);
        charLayoutManager = new GridLayoutManager(this,3);
        charRecyclerView.setLayoutManager(charLayoutManager);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url2 ="https://gateway.marvel.com/v1/public/characters?ts=1590763605&apikey=85945ebf049310af9b57bdd9b21361ac&hash=2371fd05d862c167376b22e74613e634";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                model = new Gson().fromJson(response, JsonMarvelModel.class);
                Log.i("RESPONSE", model.toString());
                filllist();
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

    }

    public void setcharacter(int pos){
        Intent intent = new Intent(this, SetCharacter.class);
        intent.putExtra("nickname",playername.getText());
        intent.putExtra("characterdata",model.getData().getResults().get(pos));
        startActivity(intent);
    }
    public void filllist(){
        int countlist= model.getData().getResults().size();

        for (int i=0;i<countlist;i++) {
            String iconpath;
            String charname;
            imageresource=0;
            charname = model.getData().getResults().get(i).getName();
            iconpath = model.getData().getResults().get(i).getThumbnail().getPath() + '.'+ model.getData().getResults().get(i).getThumbnail().getExtension();
//            for(String x:data){
//                int id = model.getData().getResults().get(i).getId();
//                if(x.equals(String.valueOf(id))){
//                    imageresource=R.id.favouriteicon;
//                }
//                else{
//                    imageresource=0;
//                }
//            }
            charsList.add(new ListItem(iconpath,imageresource,charname,i));

        }
        charAdapter = new CharAdapter(charsList);
        charRecyclerView.setAdapter(charAdapter);
        charAdapter.setOnItemClickListener(new CharAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position){
                int pos=charsList.get(position).getPos();
                setcharacter(pos);

            }
        });
        findfavourite();


    }
    public void setfavourites(){
        for (int i=0;i<model.getData().getResults().size();i++) {
            for(String x:data){
                int id = model.getData().getResults().get(i).getId();
                if(x.equals(String.valueOf(id))){
                    charsList.get(i).setFavouriteImageResource(R.id.favouriteicon);

                }
            }
        }
        //charAdapter.notifydatachange(charsList);
    }

    public void findfavourite() {
        data= new ArrayList<>();
        firebase = FirebaseDatabase.getInstance();
        reference = firebase.getReference().child("favourites").child(nickname);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot x:dataSnapshot.getChildren()){

                    data.add(x.getValue().toString());
                }

                //setfavourites();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void buildRecyclerView(){

        charAdapter = new CharAdapter(charsList);
        charRecyclerView.setAdapter(charAdapter);
        charAdapter.setOnItemClickListener(new CharAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position){
                int pos=charsList.get(position).getPos();
                setcharacter(pos);

            }
        });
    }
}
