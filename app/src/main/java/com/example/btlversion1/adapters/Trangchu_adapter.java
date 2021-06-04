package com.example.btlversion1.adapters;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlversion1.MainActivity2;
import com.example.btlversion1.R;
import com.example.btlversion1.data.DataBase;
import com.example.btlversion1.data.models.Daluu;
import com.example.btlversion1.data.models.Trangchu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Trangchu_adapter extends RecyclerView.Adapter<Trangchu_adapter.ViewHolder> {
    private NotificationManagerCompat notificationManagerCompat;
    private Context context;
    private ArrayList<Trangchu> mTrangchus;
    private DataBase db;
    private static final String CHANNEL_1_ID = "channel1";
    public Trangchu_adapter(Context context, ArrayList<Trangchu> mTrangchus) {
        this.context = context;
        this.mTrangchus = mTrangchus;
    }
    public void setData(ArrayList<Trangchu> mTrangchus){
        this.mTrangchus = mTrangchus;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        notificationManagerCompat = NotificationManagerCompat.from(context);
        final Trangchu trangchu = mTrangchus.get(position);
        if(!trangchu.img.equals("")){
            Picasso.with(context).load(trangchu.img).into(holder.imgview);
        }
        db=new DataBase(context);
        holder.textView.setText(trangchu.getTitle());
        holder.textViewtime.setText(trangchu.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("URL",trangchu.getLink());
                context.startActivity(intent);
            }
        });
        holder.btnsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    sendOnChannel();
                    String ten = trangchu.title;
                    String img =trangchu.img;
                    String link = trangchu.link;
                    String time = trangchu.time;

                    Daluu daLuu=new Daluu(ten,img,link,time);
                    db.addTinTuc(daLuu);
                    Toast.makeText(v.getContext(),"Lưu Thành Công!!" + "", Toast.LENGTH_SHORT).show();


                }catch(Exception e){
                    Toast.makeText(v.getContext(),"Lưu thất bại!!" + "", Toast.LENGTH_SHORT).show();
                }
            }

            private void sendOnChannel() {
                String title=trangchu.title;
                String message=trangchu.link;
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
        return mTrangchus.size();
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
