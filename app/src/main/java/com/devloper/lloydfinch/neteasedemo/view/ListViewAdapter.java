package com.devloper.lloydfinch.neteasedemo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.layout.simple_list_item_1;

/**
 * Name: ListViewAdapter
 * Author: lloydfinch
 * Function:
 * Date: 2020-01-07 11:16
 * Modify: lloydfinch 2020-01-07 11:16
 */
public class ListViewAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(simple_list_item_1, parent, false);
        textView.setText("position:" + position);
        textView.setOnClickListener(v -> {
            Toast.makeText(parent.getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
        });
        return textView;
    }
}
