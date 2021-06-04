package com.example.btlversion1.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.btlversion1.R;
import com.example.btlversion1.adapters.Trangchu_adapter;
import com.example.btlversion1.data.api.Api_Rss;
import com.example.btlversion1.data.models.Trangchu;
import com.example.btlversion1.data.rss.trangchu.Rss;
import com.example.btlversion1.data.rss.trangchu.RssItem;
import com.example.btlversion1.data.rss.trangchu.Rssdescription;
import com.example.btlversion1.data.rss.trangchu.Rssimg;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.SimpleXmlConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangchuFragment} factory method to
 * create an instance of this fragment.
 */
public class TrangchuFragment extends Fragment {

    private static final String TAG = "TrangchuFragment";
    private ArrayList<Trangchu> trangchucArrayList;
    private RecyclerView recyclerView;
    private Trangchu_adapter adapter;
    private Context context;
    View view;
    private static final String BASE_URL = "https://cdn.24h.com.vn/";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trangchucArrayList = new ArrayList<Trangchu>();
       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        Api_Rss api_rss = retrofit.create(Api_Rss.class);
        Call<Rss> call = api_rss.getRss();
        call.enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                String title="";
                String img  ="";
                String link="";
                String time="";
                //trangchucArrayList = new ArrayList<Trangchu>();
                List<RssItem> rssItems = response.body().getChannel().getItem();
                for(int i=1;i<rssItems.size();i++){
                    title = rssItems.get(i).getTitle();
                    List<Rssdescription> rssdescriptions =rssItems.get(i).getMdescriptionList();
                    List<Rssimg> rssimg = rssdescriptions.get(0).getListimg();
                    if(!rssimg.get(0).getSrc().equals("")){
                        img = rssimg.get(0).getSrc();
                    }
                    link = rssItems.get(i).getLink();
                    time = rssItems.get(i).getPubDate();
                    trangchucArrayList.add(new Trangchu(title,img,link,time));
                }

                adapter = new Trangchu_adapter(getActivity(),trangchucArrayList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
               }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.d(TAG," expashi:::: "+t.getMessage());

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_trangchu, container, false);
        recyclerView =(RecyclerView) view.findViewById(R.id.recycleview02);
        return view;
    }
}