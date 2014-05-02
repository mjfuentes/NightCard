package com.mjfuentes.nightcard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mjfuentes.nightcard.Model.Trago;
import com.mjfuentes.nightcard.R;

import java.util.List;

/**
 * Created by matias on 02/05/14.
 */
public class ListAdapter extends BaseAdapter {

    private List<Trago> tragos;
    private LayoutInflater inflater;
    private ViewGroup container;

    public ListAdapter(LayoutInflater inflater, ViewGroup container, List<Trago> tragos) {
        this.tragos = tragos;
        this.container = container;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return tragos.size();
    }

    @Override
    public Object getItem(int i) {
        return tragos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RelativeLayout layout = (RelativeLayout) this.inflater.inflate(R.layout.drink_list_item,this.container);
        TextView title = (TextView) layout.findViewById(R.id.title);
        title.setText(tragos.get(i).getName());
        TextView description = (TextView) layout.findViewById(R.id.description);
        description.setText(tragos.get(i).getDescription());
        return layout;
    }

    public List<Trago> getTragos() {
        return tragos;
    }

    public void setTragos(List<Trago> tragos) {
        this.tragos = tragos;
    }
}