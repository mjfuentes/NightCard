package com.mjfuentes.nightcard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        RelativeLayout layout = (RelativeLayout) this.inflater.inflate(R.layout.drink_list_item,null);
        TextView title = (TextView) layout.findViewById(R.id.title);
        final TextView amountText = (TextView) layout.findViewById(R.id.selectedAmount);
        Button more = (Button) layout.findViewById(R.id.buttonMore);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = tragos.get(i).getPrice();
                tragos.get(i).setPrice(amount++);
                amountText.setText(String.valueOf(amount++));
            }
        });
        Button less = (Button) layout.findViewById(R.id.buttonLess);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = tragos.get(i).getPrice();
                if (amount > 0) {
                    tragos.get(i).setPrice(amount--);
                    amountText.setText(String.valueOf(amount--));
                }
            }
        });
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