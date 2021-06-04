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
import com.example.btlversion1.adapters.Amthuc_adapter;
import com.example.btlversion1.data.api.Api_Rss_Amthuc;
import com.example.btlversion1.data.models.Amthuc;
import com.example.btlversion1.data.rss.amthuc.Rss;
import com.example.btlversion1.data.rss.amthuc.RssItem;

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
 * Use the {@link AmthucFragment} factory method to
 * create an instance of this fragment.
 */
public class AmthucFragment extends Fragment {

    private static final String TAG = "AmthucFrament";
    private ArrayList<Amthuc> amthucArrayList;
    private RecyclerView recyclerView;
    private Amthuc_adapter adapter;
    private Context context;
    View view;
    private static final String BASE_URL = "https://cdn.24h.com.vn/";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amthucArrayList =new ArrayList<Amthuc>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        Api_Rss_Amthuc apiRssAmthuc = retrofit.create(Api_Rss_Amthuc.class);
        Call<Rss> rssCall = apiRssAmthuc.getRss();
        rssCall.enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                String title="";
                String img  ="";
                String link="";
                String time="";
                List<RssItem> rssItems = response.body().getChannel().getItem();
                for(int i=1;i<rssItems.size();i++){
                    title = rssItems.get(i).getTitle();
                    String cdata = rssItems.get(i).getDescription();
                    Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                    Matcher matcher =p.matcher(cdata);
                    if(matcher.find()){
                        img= matcher.group(1);
                    }
                    link = rssItems.get(i).getLink();
                    time = rssItems.get(i).getPubDate();
                    amthucArrayList.add(new Amthuc(title,img,link,time));
                }
                adapter = new Amthuc_adapter(getActivity(),amthucArrayList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.d(TAG," test "+t.getMessage());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_amthuc,container,false);
        recyclerView =(RecyclerView) view.findViewById(R.id.recycleview06);
        return view;
    }
}