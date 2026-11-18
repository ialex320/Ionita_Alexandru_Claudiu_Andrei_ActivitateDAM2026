package com.example.seminar8_imagini;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiAvionActivity extends AppCompatActivity {

    private List<Bitmap> imagini = new ArrayList<>();
    private List<ImaginiAvion> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagini_avion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://images.unsplash.com/photo-1436491865332-7a61a109cc05?w=800");
        linkuriImagini.add("https://images.unsplash.com/photo-1540962351504-03099e0a754b?w=800");
        linkuriImagini.add("https://cdn.adh.reperio.news/image-4/43b2c239-93e4-4a3f-96bf-c1c7d3176477/index.jpeg");
        linkuriImagini.add("https://chic-escapes.ro/wp-content/uploads/2014/09/1280px-Airbus_A380_overfly-900x444.jpg");
        linkuriImagini.add("https://stirileprotv.ro/img/2019/02/13/220000/62021472.jpg");

        ListView lv = findViewById(R.id.imaginiAvion_LV);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link:linkuriImagini)
                {
                    Bitmap imagine = null;
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagine = BitmapFactory.decodeStream(is);
                        is.close();
                    } catch (IOException e) {
                    } finally {
                        if (con != null) {
                            con.disconnect();
                        }
                    }
                    imagini.add(imagine);
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista.add(new ImaginiAvion(imagini.get(0), "https://images.unsplash.com/photo-1436491865332-7a61a109cc05?w=800", "Avion 1"));
                        lista.add(new ImaginiAvion(imagini.get(1), "https://images.unsplash.com/photo-1540962351504-03099e0a754b?w=800", "Avion 2"));
                        lista.add(new ImaginiAvion(imagini.get(2), "https://cdn.adh.reperio.news/image-4/43b2c239-93e4-4a3f-96bf-c1c7d3176477/index.jpeg", "Avion 3"));
                        lista.add(new ImaginiAvion(imagini.get(3), "https://chic-escapes.ro/wp-content/uploads/2014/09/1280px-Airbus_A380_overfly-900x444.jpg", "Avion 4"));
                        lista.add(new ImaginiAvion(imagini.get(4), "https://stirileprotv.ro/img/2019/02/13/220000/62021472.jpg", "Avion 5"));

                        AdaptorImagine adaptor = new AdaptorImagine(lista, ImaginiAvionActivity.this, R.layout.activity_adaptor_imagine);
                        lv.setAdapter(adaptor);
                    }
                });
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                it.putExtra("link", lista.get(position).getLink());
                startActivity(it);
            }
        });
    }
}
