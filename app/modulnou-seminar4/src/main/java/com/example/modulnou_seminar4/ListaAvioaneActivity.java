package com.example.modulnou_seminar4;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.modulnou_seminar4.MainActivity.avioane;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ListaAvioaneActivity extends AppCompatActivity {

    private List<Avion> avioane = null;
    private int idModificat = 0;
    private AvionAdapter adapter = null;

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
//        ArrayAdapter<Avion> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, avioane);
//        lv.setAdapter(adapter);

        adapter = new AvionAdapter(avioane, getApplicationContext(), R.layout.avion_layout);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AdaugaAvionActivity.class);
                intent.putExtra("Avion", avioane.get(position));

                idModificat = position;
                startActivityForResult(intent, 209);

                Toast.makeText(getApplicationContext(), avioane.get(position).toString(), Toast.LENGTH_SHORT).show();


            }
        });

        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            avioane.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Avion sters", LENGTH_LONG).show();
            return true;
        });

        Intent it = getIntent();

        if (it.hasExtra("Avion"))
        {
            Avion avion = it.getParcelableExtra("Avion");
            EditText modelET = findViewById(R.id.model_ET);
            EditText nrLocuriET = findViewById(R.id.nrLocuri_ET);

            modelET.setText(avion.getModelAvion());
            nrLocuriET.setText(avion.getNrLocuri());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == 209)
            {
                avioane.set(idModificat, data.getParcelableExtra("Avion"));
                adapter.notifyDataSetChanged();
            }
        }
    }
}