package com.example.modulnou_seminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AvionAdapter extends BaseAdapter {

    private List<Avion> avioane = null;
    private Context ctx;
    private int resursaLayout;

    public AvionAdapter(List<Avion> avioane, Context ctx, int resursaLayout) {
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
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, parent, false);

        TextView modelAvion = v.findViewById(R.id.modelAvion);
        TextView nrLocuri = v.findViewById(R.id.nrLocuri_ET);

        Avion avion = (Avion)getItem(position);

        modelAvion.setText(avion.getModelAvion());
        modelAvion.setText(avion.getNrLocuri());
        return v;
    }


}
