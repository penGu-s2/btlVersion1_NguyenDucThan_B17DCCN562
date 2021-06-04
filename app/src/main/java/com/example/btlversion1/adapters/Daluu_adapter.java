package com.example.btlversion1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlversion1.MainActivity2;
import com.example.btlversion1.R;
import com.example.btlversion1.data.DataBase;
import com.example.btlversion1.data.models.Daluu;
import com.example.btlversion1.data.models.Trangchu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Daluu_adapter extends RecyclerView.Adapter<Daluu_adapter.ViewHolder> {
    private Context context;
    private ArrayList<Daluu> mdaluu;
    private DataBase db;


    public Daluu_adapter(Context context,ArrayList<Daluu> mdaluu){
        this.context=context;
        this.mdaluu=mdaluu;
    }
    public void setMdaLuu(ArrayList<Daluu> list){
        this.mdaluu=list;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {

        final Daluu daLuu = mdaluu.get(position);

        if(!daLuu.img.equals("")){
            Picasso.with(context).load(daLuu.img).into(holder.imgview);
        }
        db=new DataBase(context);
        holder.textView.setText(daLuu.getTitle());
        holder.textViewtime.setText(daLuu.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    try {
                String link=daLuu.link;
                    Intent intent = new Intent(v.getContext(), MainActivity2.class);
                    intent.putExtra("URL",link);
                v.getContext().startActivity(intent);
              //  }
             //   catch (Exception e){
             //       Toast.makeText(v.getContext(),"Không Thành Công!!" + "", Toast.LENGTH_SHORT).show();
             //   }
            }
        });
        holder.btnsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    //int id=holder.itemView.getId();\
                    int id=(position);
                    //int id = mdaluu.get(position);

                    db.deleteItem(id);
                    Toast.makeText(v.getContext(),"Xoá Thành Công!!" + "", Toast.LENGTH_SHORT).show();
                }catch (Exception e){Toast.makeText(v.getContext(),"Không Thành Công!!" + "", Toast.LENGTH_SHORT).show();}
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdaluu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgview;
        private TextView textView;
        private TextView textViewtime;
        private ImageView btnsave;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgview =(ImageView) itemView.findViewById(R.id.img01);
            textView=(TextView) itemView.findViewById(R.id.txtview01);
            textViewtime=(TextView) itemView.findViewById(R.id.txtviewtime);
            btnsave = (ImageView) itemView.findViewById(R.id.btnsave);
        }
    }
}
