package com.example.asus.duoihinhbatchu.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.asus.duoihinhbatchu.Common.Common;
import com.example.asus.duoihinhbatchu.MainActivity;

import java.util.List;

/**
 * Created by ASUS on 11/11/17.
 */

public class GridviewSuggestAdapter extends BaseAdapter {

    private List<String> suggestSource;
    private Context context;
    private MainActivity mainActivity;

    public GridviewSuggestAdapter(List<String> suggestSource, Context context, MainActivity mainActivity) {
        this.suggestSource = suggestSource;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
        if(convertView == null){
            if(suggestSource.get(position).equals("null")){
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);

            }else {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setText(suggestSource.get(position));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //if correct answer contain character user selecter
                        if(String.valueOf(mainActivity.answer).contains(suggestSource.get(position)))
                        {
                            char compare = suggestSource.get(position).charAt(0);//getchar
                            for (int i = 0; i<mainActivity.answer.length;i++)
                            {
                                if(compare == mainActivity.answer[i])
                                    Common.user_submit_answer[i]= compare;
                            }
                            //update ui
                            GridviewAnswerAdapter answerAdapter = new GridviewAnswerAdapter(Common.user_submit_answer,context);
                            mainActivity.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            //remote from suggest source
                            mainActivity.suggestSource.set(position, "null");
                            mainActivity.suggestAdapter = new GridviewSuggestAdapter(mainActivity.suggestSource,context,mainActivity);
                            mainActivity.gridViewSuggest.setAdapter(mainActivity.suggestAdapter);
                            mainActivity.suggestAdapter.notifyDataSetChanged();
                        }
                        else {//else
                            mainActivity.suggestSource.set(position, "null");
                            mainActivity.suggestAdapter = new GridviewSuggestAdapter(mainActivity.suggestSource,context,mainActivity);
                            mainActivity.gridViewSuggest.setAdapter(mainActivity.suggestAdapter);
                            mainActivity.suggestAdapter.notifyDataSetChanged();

                        }
                    }
                });

            }


        }else
            button = (Button) convertView;

        return button;
    }
}
