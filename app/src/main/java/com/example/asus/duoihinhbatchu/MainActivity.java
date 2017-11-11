package com.example.asus.duoihinhbatchu;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.duoihinhbatchu.Adapter.GridviewAnswerAdapter;
import com.example.asus.duoihinhbatchu.Adapter.GridviewSuggestAdapter;
import com.example.asus.duoihinhbatchu.Common.Common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public List<String> suggestSource = new ArrayList<>();

    public GridviewAnswerAdapter answerAdapter;
    public GridviewSuggestAdapter suggestAdapter;
    public Button btnsubmit;
    public GridView gridViewAnswer, gridViewSuggest;
    public ImageView imgViewQuestion;
    int[]image_list={
            R.drawable.apple,
            R.drawable.facebook,

            R.drawable.git,
            R.drawable.instagram,
            R.drawable.soundcloud,
            R.drawable.tomdallimore,
            R.drawable.twitter,
            R.drawable.yamaha

    };

    public char[] answer;
    String correct_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        
    }

    private void initView() {
        gridViewAnswer = (GridView) findViewById(R.id.gridviewAnswer);
        gridViewSuggest = (GridView) findViewById(R.id.gridviewSugges);

        imgViewQuestion = (ImageView) findViewById(R.id.img_logo);
        //add setup
        setupList();

        btnsubmit = (Button) findViewById(R.id.btn_submit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="";
                for (int i=0; i< Common.user_submit_answer.length;i++)
                    result+=String.valueOf(Common.user_submit_answer[i]);
                //kiem tra ket qua
                if (result.equals(correct_answer)){
                    Toast.makeText(getApplicationContext(), "Finish! this is "+result, Toast.LENGTH_SHORT).show();
                    //reset
                    Common.count=0;
                    Common.user_submit_answer = new char[correct_answer.length()];

                    //set adapter
                    GridviewAnswerAdapter answerAdapter = new GridviewAnswerAdapter(setupNullList(),getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);

                    GridviewSuggestAdapter suggestAdapter = new GridviewSuggestAdapter(suggestSource,getApplicationContext(),MainActivity.this);
                    gridViewSuggest.setAdapter(suggestAdapter);

                    suggestAdapter.notifyDataSetChanged();

                    setupList();
                    
                }else {
                    Toast.makeText(MainActivity.this, "Incorrect!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupList() {
        //random logo
        Random randmon = new Random();
        int imageSelected = image_list[randmon.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageSelected);

        correct_answer= getResources().getResourceName(imageSelected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/")+1);
        answer =correct_answer.toCharArray();

        Common.user_submit_answer = new char[answer.length];

        //add answer charater to list
        suggestSource.clear();
        for (char item:answer){
            //add logo name to lsit
            suggestSource.add(String.valueOf(item));
        }
        //randmon 1 vai kí tu to list
        for(int i = answer.length; i<=answer.length*2;i++)
            suggestSource.add(Common.alphabet_character[randmon.nextInt(Common.alphabet_character.length)]);

        //sort random
        Collections.shuffle(suggestSource);

        //set for Gridview
        answerAdapter = new GridviewAnswerAdapter(setupNullList(),this);
        suggestAdapter = new GridviewSuggestAdapter(suggestSource,this,this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);
    }

    private char[] setupNullList() {

        char result[]=new char[answer.length];
        for (int i =0; i<answer.length; i++){
            result[i]=' ';
        }
        return result;

    }
}
