package com.example.modulnou_seminar4;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.modulnou_seminar4.MainActivity.avioane;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListaAvioaneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_avioane);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv = findViewById(R.id.avioane_LV);
        ArrayAdapter<Avion> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, avioane);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Avion avion = avioane.get(position);
            Toast.makeText(this, avion.toString(), LENGTH_LONG).show();
        });

        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            avioane.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Avion sters", LENGTH_LONG).show();
            return true;
        });
    }
}