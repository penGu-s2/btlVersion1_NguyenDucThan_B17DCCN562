package com.example.btlversion1.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.btlversion1.R;
import com.example.btlversion1.adapters.Congnghe_adapter;
import com.example.btlversion1.data.api.Api_Rss_Congnghe;
import com.example.btlversion1.data.models.Congnghe;
import com.example.btlversion1.data.rss.congnghe.Rss;
import com.example.btlversion1.data.rss.congnghe.RssItem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.SimpleXmlConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CongngheFragment} factory method to
 * create an instance of this fragment.
 */
public class CongngheFragment extends Fragment {

    private static final String TAG = "ThethaoFragment";
    private ArrayList<Congnghe> congngheArrayList;
    private RecyclerView recyclerView;
    private Congnghe_adapter adapter;
    private Context context;
    View view;
    private static final String BASE_URL = "https://cdn.24h.com.vn/";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        congngheArrayList = new ArrayList<Congnghe>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        Api_Rss_Congnghe api_rss = retrofit.create(Api_Rss_Congnghe.class);
        Call<Rss> call = api_rss.getRss();
        call.enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                String title="";
                String img  ="";
                String link="";
                String time="";
                List<RssItem> rssItems = response.body().getChannel().getItem();
                //Log.d(TAG,"data rssItems::::  " + response.body().getChannel());
                for(int i=1;i<rssItems.size();i++){
                    title = rssItems.get(i).getTitle();
                    String cdata = rssItems.get(i).getDescription();
                    Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                    Matcher matcher =p.matcher(cdata);
                    if(matcher.find()){
                        img= matcher.group(1);
                    }
                    Log.d(TAG,"data thethao::::  " +"   _+_+_+   "+img);
                    link = rssItems.get(i).getLink();
                    time = rssItems.get(i).getPubDate();
                    congngheArrayList.add(new Congnghe(title,img,link,time));
                }
                Log.d("testt::: ","aaaaaaaaaaaaaaaaaaa   Thethao");
                adapter = new Congnghe_adapter(getActivity(),congngheArrayList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
               // Log.d(TAG,"data end::::  Thethao "+thethaoArrayList.size()+"  ++= ++   "+img);
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.d(TAG," expashi:::: "+t.getMessage());
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_congnghe,container,false);
        recyclerView =(RecyclerView) view.findViewById(R.id.recycleview04);
        return view;
    }
}
