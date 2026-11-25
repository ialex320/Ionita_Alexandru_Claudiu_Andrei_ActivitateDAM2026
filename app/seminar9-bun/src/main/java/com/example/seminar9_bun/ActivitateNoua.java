package com.example.seminar9_bun;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivitateNoua extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_noua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
//    curl 'https://dataservice.accuweather.com/locations/v1/cities/search?q=<string>' \
//            --header 'authorization: Bearer CHEIA_TA'



    public void apeleazaAPI(View view) {

        EditText etOras = findViewById(R.id.numeOras_ET);
        TextView tvRezultat = findViewById(R.id.rezultat_TV);
        String oras = etOras.getText().toString();
        Handler handler = new Handler(Looper.myLooper());
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String link = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey=CHEIA_TA&q=" + oras;
                try {
                    URL url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Authorization", "Bearer CHEIA_TA");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder builder = new StringBuilder("");
                    String linie;
                    while ((linie = reader.readLine()) != null) {
                        builder.append(linie);
                    }
                    JSONArray vector = new JSONArray(builder.toString());
                    JSONObject obiect = vector.getJSONObject(0);
                    String cheieOras = obiect.getString("Key");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), cheieOras, Toast.LENGTH_LONG).show();
                            tvRezultat.setText(cheieOras);
                        }
                    });
                } catch (Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }
}