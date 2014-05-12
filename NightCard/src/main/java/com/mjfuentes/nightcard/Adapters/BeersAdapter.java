package com.mjfuentes.nightcard.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mjfuentes.nightcard.Controller.DrinksController;
import com.mjfuentes.nightcard.Model.Trago;
import com.mjfuentes.nightcard.R;

import java.util.List;

/**
 * Created by matias on 02/05/14.
 */
public class BeersAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ViewGroup container;

    public BeersAdapter(LayoutInflater inflater, ViewGroup container) {
        this.container = container;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return DrinksController.getCervezas().size();
    }

    @Override
    public Object getItem(int i) {
        return DrinksController.getCervezas().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        RelativeLayout layout = (RelativeLayout) this.inflater.inflate(R.layout.drink_list_item,null);
        TextView title = (TextView) layout.findViewById(R.id.title);
        Button more = (Button) layout.findViewById(R.id.buttonMore);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = DrinksController.getCervezas().get(i).getStock();
                DrinksController.getCervezas().get(i).setStock(amount++);
                notifyDataSetChanged();
            }
        });
        Button less = (Button) layout.findViewById(R.id.buttonLess);
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = DrinksController.getCervezas().get(i).getStock();
                if (amount > 0) {
                    DrinksController.getCervezas().get(i).setStock(amount--);
                    notifyDataSetChanged();
                }
            }
        });
        title.setText(DrinksController.getCervezas().get(i).getName());
        TextView description = (TextView) layout.findViewById(R.id.description);
        description.setText(DrinksController.getCervezas().get(i).getDescription());
        TextView quantity = (TextView) layout.findViewById(R.id.selectedAmount);
        quantity.setText(String.valueOf(DrinksController.getCervezas().get(i).getStock()));
        return layout;
    }
}