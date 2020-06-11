package com.example.marvel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CharactersActivity extends AppCompatActivity  {

    TextView playername;
    SearchView searchbar;
    private RecyclerView charRecyclerView;
    private CharAdapter charAdapter;
    FirebaseDatabase firebase;
    DatabaseReference reference;
    String nickname;
    ArrayList<ListItem> charsList;
    ArrayList<MarvelResultsModel> marvelData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        playername=findViewById(R.id.PlayerName);
        nickname=getIntent().getStringExtra("nickname");
        playername.setText(nickname);
        charsList = new ArrayList<>();
        marvelData = new ArrayList<>();
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
        charRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

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
