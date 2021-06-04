package com.example.btlversion1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.btlversion1.R;
import com.example.btlversion1.adapters.Daluu_adapter;
import com.example.btlversion1.data.DataBase;
import com.example.btlversion1.data.api.Api_Rss;
import com.example.btlversion1.data.models.Daluu;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.SimpleXmlConverterFactory;

public class MainActivity3 extends AppCompatActivity {
    private ArrayList<Daluu> list;
    private RecyclerView recyclerView;
    private Daluu_adapter adapter;
    private Context context;
    private DataBase db;
    private static final String BASE_URL = "https://cdn.24h.com.vn/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        recyclerView=findViewById(R.id.recycledaluu);
        db=new DataBase(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Daluu_adapter(context,list);
        list=db.allDaLuu();
        adapter.setMdaLuu(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusearch, menu);
        MenuItem item = menu.findItem(R.id.mSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.setMdaLuu(db.readItemBySearchKey(newText));
                return true;
            }
        });
        return true;
    }

}