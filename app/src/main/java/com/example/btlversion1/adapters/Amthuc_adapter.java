package com.example.btlversion1.adapters;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlversion1.MainActivity2;
import com.example.btlversion1.R;
import com.example.btlversion1.data.DataBase;
import com.example.btlversion1.data.models.Amthuc;
import com.example.btlversion1.data.models.Daluu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Amthuc_adapter extends RecyclerView.Adapter<Amthuc_adapter.ViewHolder>{
    private Context context;
    private ArrayList<Amthuc> mamthuc;
    private DataBase db;
    private NotificationManagerCompat notificationManagerCompat;

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
        notificationManagerCompat = NotificationManagerCompat.from(context);
        final Amthuc amthuc = mamthuc.get(position);
        if(!amthuc.img.equals("")){
            Picasso.with(context).load(amthuc.img).into(holder.imgview);
        }
        db=new DataBase(context);
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
        holder.btnsave.setOnClickListener(new View.OnClickListener() {

            private static final String CHANNEL_1_ID = "channel1";

            @Override
            public void onClick(View v) {
                try{
                    sendOnChannel();
                    String ten = amthuc.title;
                    String img =amthuc.img;
                    String link = amthuc.link;
                    String time = amthuc.time;

                    Daluu daLuu=new Daluu(ten,img,link,time);
                    db.addTinTuc(daLuu);
                    Toast.makeText(v.getContext(),"Lưu Thành Công!!" + "", Toast.LENGTH_SHORT).show();


                }catch(Exception e){
                    Toast.makeText(v.getContext(),"Lưu thất bại!!" + "", Toast.LENGTH_SHORT).show();
                }
            }

            private void sendOnChannel() {
                String title=amthuc.title;
                String message=amthuc.link;
                Notification notification= new
                        NotificationCompat.Builder(context,CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.save)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .setCategory(NotificationCompat.CATEGORY_PROMO)
                        .build();
                int notificationId = 1;
                notificationManagerCompat.notify(notificationId,
                        notification);
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
