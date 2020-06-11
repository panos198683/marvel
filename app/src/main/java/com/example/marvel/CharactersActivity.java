package com.example.marvel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.marvel.json.JsonMarvelModel;
import com.example.marvel.json.MarvelResultsModel;
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

    TextView playername,favouritebutton,allbutton;
    SearchView searchbar;
    ImageView personimage;
    private RecyclerView charRecyclerView;
    private CharAdapter charAdapter;
    FirebaseDatabase firebase;
    DatabaseReference reference;
    String nickname;
    ArrayList<ListItem> charsList;
    ArrayList<MarvelResultsModel> marvelData;
    boolean clickedfavourite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        playername=findViewById(R.id.PlayerName);
        personimage=findViewById(R.id.personimage);
        nickname=getIntent().getStringExtra("nickname");
        playername.setText(nickname);
        charsList = new ArrayList<>();
        marvelData = new ArrayList<>();
        searchbar = findViewById(R.id.SearchView);
        favouritebutton = findViewById(R.id.favourite);
        allbutton = findViewById(R.id.allchars);

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
        favouritebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clickedfavourite){
                    favouritebutton.setTextColor(Color.parseColor("#ffffff"));
                    allbutton.setTextColor(Color.parseColor("#A4A4A4"));
                    clickedfavourite=true;
                    charAdapter.showOnlyFavorite(clickedfavourite);
                    searchbar.setQuery("",false);
                    searchbar.clearFocus();
                }
            }
        });
        allbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickedfavourite){
                    favouritebutton.setTextColor(Color.parseColor("#A4A4A4"));
                    allbutton.setTextColor(Color.parseColor("#ffffff"));
                    clickedfavourite=false;
                    charAdapter.showOnlyFavorite(clickedfavourite);
                    searchbar.setQuery("",false);
                    searchbar.clearFocus();
                }
            }
        });
        playername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoprofile();
            }
        });
        personimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoprofile();
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        charRecyclerView = findViewById(R.id.recycleview);
        charRecyclerView.setHasFixedSize(true);
        charRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        RequestQueue queue = Volley.newRequestQueue(this);
        String url2 ="https://gateway.marvel.com/v1/public/characters?ts=1590763605&apikey=85945ebf049310af9b57bdd9b21361ac&hash=2371fd05d862c167376b22e74613e634";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JsonMarvelModel model = new Gson().fromJson(response, JsonMarvelModel.class);
                Log.i("RESPONSE", model.toString());
                fillList(model);

                findfavourite();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);

    }


    public void gotoprofile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("nickname",playername.getText());
        startActivityForResult(intent,2000);
    }
    public void setcharacter(MarvelResultsModel data) {
        Intent intent = new Intent(this, SetCharacter.class);
        intent.putExtra("nickname",playername.getText());
        intent.putExtra("characterdata", data);
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        findfavourite();
    }

    public void fillList(JsonMarvelModel model) {
        int countlist= model.getData().getResults().size();

        for (int i=0;i<countlist;i++) {
            MarvelResultsModel data = model.getData().getResults().get(i);
            String charname = data.getName();
            String iconpath = data.getThumbnail().getPath() + '.' + data.getThumbnail().getExtension();

            marvelData.add(data);
            charsList.add(new ListItem(data.getId(), iconpath, charname, i));
        }

        charAdapter = new CharAdapter(charsList);
        charRecyclerView.setAdapter(charAdapter);

        charAdapter.setOnItemClickListener(new CharAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position){
                ListItem item = charsList.get(position);

                for (MarvelResultsModel model : marvelData) {
                    if (model.getName().equals(item.getCharName())) {
                        setcharacter(model);
                        break;
                    }
                }
            }
        });
    }

    public void findfavourite() {
        firebase = FirebaseDatabase.getInstance();
        reference = firebase.getReference().child("favourites").child(nickname);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> data = new ArrayList<>();

                for(DataSnapshot x:dataSnapshot.getChildren()){
                    data.add(String.valueOf(x.getValue()));
                }

                charAdapter.updateFavorites(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
