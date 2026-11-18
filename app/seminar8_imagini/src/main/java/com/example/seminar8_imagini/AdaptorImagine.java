    package com.example.seminar8_imagini;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

    public class AdaptorImagine extends BaseAdapter {

    private List<ImaginiAvion> avioane = null;
    private Context ctx;
    private int resursaLayout;

        public AdaptorImagine(List<ImaginiAvion> avioane, Context ctx, int resursaLayout) {
            this.avioane = avioane;
            this.ctx = ctx;
            this.resursaLayout = resursaLayout;
        }

        @Override
    public int getCount() {
        return avioane.size();
    }

    @Override
    public Object getItem(int position) {
        return avioane.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(ctx);
        View view = inflate.inflate(resursaLayout, parent, false);

        ImaginiAvion imagine = avioane.get(position);

        TextView tvDescriere = view.findViewById(R.id.imagini_TV);
        tvDescriere.setText(imagine.getTextAfisat());

        ImageView ivImagine = view.findViewById(R.id.imagine_IV);
        ivImagine.setImageBitmap(imagine.getImagine());


        return view;
    }
}