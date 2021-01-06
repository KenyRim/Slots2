package com.appdev.slots2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.appdev.slots2.adapters.SlotsRVAdapter;
import com.appdev.slots2.utils.SmoothScrollLM;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SlotsActivity extends AppCompatActivity
        implements SlotsRVAdapter.OnScrollResult {
    private RecyclerView rv1, rv2, rv3;
    private SlotsRVAdapter adapter;
    private ArrayList<String> data;
    private List<String> temp;
    private TextView tvResult;
    private SmoothScrollLM lm;
    private List<Integer> stateList = new ArrayList<>();
    private String[] resultArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slots_activity);

        rv1 = findViewById(R.id.rv_slots_1);
        rv2 = findViewById(R.id.rv_slots_2);
        rv3 = findViewById(R.id.rv_slots_3);

        addRecyclerView(rv1);
        addRecyclerView(rv2);
        addRecyclerView(rv3);

        Button btnSpin = findViewById(R.id.btn_spin);
        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp.clear();
                stateList.clear();
                tvResult.setText("");

                startWithDeley(0,rv1);
                startWithDeley(500,rv2);
                startWithDeley(1000,rv3);

            }
        });

        tvResult = findViewById(R.id.tv_result);


    }

    private void startWithDeley(int delay,final RecyclerView rv) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rv.smoothScrollToPosition(getCurrentItem(rv) + 50);
            }
        },delay);

    }

    private void addRecyclerView(RecyclerView rv) {

        data = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            data.add(i + "");
        }

        lm = new SmoothScrollLM(this);
        Collections.shuffle(data);
        adapter = new SlotsRVAdapter(this,data,this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        rv.smoothScrollToPosition(3);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv);

        addListener(rv);

    }

    private void addListener(RecyclerView rv){
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView,int dx,int dy) {
                super.onScrolled(recyclerView,dx,dy);
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView,int newState) {
                super.onScrollStateChanged(recyclerView,newState);


                if (newState == 0) {
                    stateList.add(newState);
                    Log.e("aaaaaaaaaaaa","size " + stateList.size());
                    if (stateList.size() == 6){
                        for (String word:resultArr) {
                            Log.e("bbbbbbb","slot " + word);
                        }


                    }
                }
            }
        });


    }

    private int getCurrentItem(RecyclerView rv) {
        return ((LinearLayoutManager) rv.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

    @Override
    public void getScrollResult(List<String> result,int position) {
        tvResult.setText("");
        temp = result;

        for (int i = 0; i < result.size() - 2; i++) {
            if (i > result.size() - 6) {
                String resultString = tvResult.getText() + " " + temp.get(i);
                tvResult.setText(resultString);

                resultArr = resultString.split("\\s+");
                for (int i1 = 0; i1 < resultArr.length; i1++) {

                    Log.e("","");
                    if(resultArr[i1].length()>0 && !resultArr[i1].isEmpty()) {
                        resultArr[i1] = resultArr[i1].replaceAll("[^\\w]","");
                    }
                }
            }
        }
    }


}

