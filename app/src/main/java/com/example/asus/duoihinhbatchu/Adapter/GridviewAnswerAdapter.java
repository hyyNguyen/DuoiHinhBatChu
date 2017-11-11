package com.example.asus.duoihinhbatchu.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.List;

/**
 * Created by ASUS on 11/11/17.
 */

public class GridviewAnswerAdapter extends BaseAdapter {
    private char[] answerCharater;
    private Context context;

    public GridviewAnswerAdapter(char[] answerCharater, Context context) {
        this.answerCharater = answerCharater;
        this.context = context;
    }

    @Override
    public int getCount() {
        return answerCharater.length;
    }

    @Override
    public Object getItem(int position) {
        return answerCharater[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if(convertView == null){
            button = new Button(context);
            button.setLayoutParams(new GridView.LayoutParams(85,85));
            button.setPadding(8,8,8,8);
            button.setBackgroundColor(Color.BLUE);
            button.setText(String.valueOf(answerCharater[position]));
        }else {
            button = (Button) convertView;
        }
        return button;
    }
}
