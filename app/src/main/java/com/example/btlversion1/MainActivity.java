package com.example.btlversion1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.btlversion1.adapters.Daluu_adapter;
import com.example.btlversion1.adapters.SectionPagerAdapter;
import com.example.btlversion1.adapters.Trangchu_adapter;
import com.example.btlversion1.data.DataBase;
import com.example.btlversion1.data.models.Daluu;
import com.example.btlversion1.data.models.Trangchu;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager view_pager;
    private TabLayout tab_layout;
    private ImageView imageView;
    private Daluu_adapter adapter;
    private ArrayList<Daluu> list;
    private DataBase db;
    private Trangchu trangchu;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannels();

        initComponent();
        imageView=(ImageView) findViewById(R.id.img01);
    }

    private void createNotificationChannels() {
    }

    private void initComponent() {
        view_pager = (ViewPager) findViewById(R.id.viewpage01);
        setupViewPager(view_pager);
        tab_layout = (TabLayout) findViewById(R.id.tabs);
        tab_layout.setupWithViewPager(view_pager);

    }

    private void setupViewPager(ViewPager view_pager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager(), 0);
        view_pager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutintuc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.set01:
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                MainActivity.this.startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*private void shareContent() {
        Bitmap bitmap = getBitmapFromView(imageView);
        String movieName = trangchu.getTitle();
        try {
            File file = new File(this.getExternalCacheDir(), "save.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_TEXT, movieName);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            bgDrawable.draw(canvas);
        }   else{
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }*/
}