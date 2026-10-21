package com.example.modulnou_seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaAvionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_avion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void salveazaAvion(View view) {
        EditText etModel = findViewById(R.id.model_ET);
        EditText etNrLocuri = findViewById(R.id.nrLocuri_ET);
        Spinner spCompanie = findViewById(R.id.companie_SP);
        RadioGroup rgTip = findViewById(R.id.tip_RG);
        CheckBox cbWifi = findViewById(R.id.wifi_CB);
        Switch swServiciu = findViewById(R.id.serviciu_SW);
        RatingBar rbRating = findViewById(R.id.rating_RB);

        String model = etModel.getText().toString().trim();
        if (model.isEmpty()) {
            etModel.setError("Introduceti modelul!");
            return;
        }

        String textLocuri = etNrLocuri.getText().toString().trim();
        if (textLocuri.isEmpty()) {
            etNrLocuri.setError("Introduceti numarul de locuri!");
            return;
        }
        int nrLocuri = Integer.parseInt(textLocuri);

        String companie = spCompanie.getSelectedItem().toString();

        RadioButton rbTip = findViewById(rgTip.getCheckedRadioButtonId());
        String tip = rbTip.getText().toString();

        boolean areWifi = cbWifi.isChecked();
        boolean esteInServiciu = swServiciu.isChecked();
        float rating = rbRating.getRating();

        Avion avion = new Avion(model, companie, nrLocuri, tip, areWifi, esteInServiciu, rating);

        Intent it = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("avion", avion);
        it.putExtras(bundle);
        setResult(RESULT_OK, it);
        finish();
    }
}