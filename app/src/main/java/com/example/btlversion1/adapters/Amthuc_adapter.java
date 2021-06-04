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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Amthuc_adapter extends RecyclerView.Adapter<Amthuc_adapter.ViewHolder>{
    private Context context;
    private ArrayList<Amthuc> mamthuc;

    public Amthuc_adapter(Context context, ArrayList<Amthuc> mamthuc) {
        this.context = context;
        this.mamthuc = mamthuc;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout,parent,false);
        Amthuc_adapter.ViewHolder viewHolder = new Amthuc_adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Amthuc amthuc = mamthuc.get(position);
        if(!amthuc.img.equals("")){
            Picasso.with(context).load(amthuc.img).into(holder.imgview);
        }
        holder.textView.setText(amthuc.title);
        holder.textViewtime.setText(amthuc.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("URL",amthuc.getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mamthuc.size();
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
