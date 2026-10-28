package com.example.modulnou_seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Avion> avioane = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void deschideAdaugare(View view)
    {
        Intent it = new Intent(this, AdaugaAvionActivity.class);
        startActivityForResult(it, 100);
    }

    public void deschideAdaugareAI(View view)
    {
        Intent it = new Intent(this, AdaugaAvionAIActivity.class);
        startActivityForResult(it, 100);
    }
    public void deschideLista(View view)
    {
        Intent it = new Intent(this, ListaAvioaneActivity.class);
        startActivity(it);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null)
        {
            Bundle bundle = data.getExtras();
            Avion avion = bundle.getParcelable("avion");
            avioane.add(avion);

            TextView tv = findViewById(R.id.avion_TV);
            tv.setText(avion.toString());

            Toast.makeText(this, avion.toString(), Toast.LENGTH_LONG).show();
        }
    }
}