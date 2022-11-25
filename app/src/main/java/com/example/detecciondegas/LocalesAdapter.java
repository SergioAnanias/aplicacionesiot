package com.example.detecciondegas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LocalesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<localModel> localModelArrayList;
    public LocalesAdapter(Context context, ArrayList<localModel> localModelArrayList) {

        this.context = context;
        this.localModelArrayList = localModelArrayList;
    }

    @Override
    public int getCount() {
        return localModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return localModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.locallist, null, true);

            holder.direccion  = (TextView) convertView.findViewById(R.id.direccion);
            holder.rut = (TextView) convertView.findViewById(R.id.rut);
            holder.nivelCO=(TextView) convertView.findViewById(R.id.nivelCO);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.direccion.setText("Dirección: "+localModelArrayList.get(position).getDireccion());
        holder.rut.setText("RUT: "+localModelArrayList.get(position).getRut());
        holder.nivelCO.setText("Niveles de CO: "+ "Place Holder");

        return convertView;
    }
    private class ViewHolder {

        protected TextView direccion, rut,nivelCO;
    }
}
