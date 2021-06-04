package com.example.btlversion1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlversion1.MainActivity2;
import com.example.btlversion1.R;
import com.example.btlversion1.data.models.Amthuc;
import com.example.btlversion1.data.models.Congnghe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Congnghe_adapter extends RecyclerView.Adapter<Congnghe_adapter.ViewHolder>{
    private Context context;
    private ArrayList<Congnghe> mCongnghes;

    public Congnghe_adapter(Context context, ArrayList<Congnghe> mCongnghes) {
        this.context = context;
        this.mCongnghes = mCongnghes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Congnghe congnghe = mCongnghes.get(position);
        if(!congnghe.img.equals("")){
            Picasso.with(context).load(congnghe.img).into(holder.imgview);
        }
        holder.textView.setText(congnghe.title);
        holder.textViewtime.setText(congnghe.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("URL",congnghe.getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCongnghes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgview;
        private TextView textView;
        private TextView textViewtime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgview =(ImageView) itemView.findViewById(R.id.img01);
            textView=(TextView) itemView.findViewById(R.id.txtview01);
            textViewtime=(TextView) itemView.findViewById(R.id.txtviewtime);
        }
    }
}
