package com.appdev.slots2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.appdev.slots2.adapters.SlotsRVAdapter;
import com.appdev.slots2.utils.SmoothScrollLM;

import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slots_activity);

        rv1 = findViewById(R.id.rv_slots_1);
        rv2 = findViewById(R.id.rv_slots_2);

        addRecyclerView(rv1);
        addRecyclerView(rv2);

        Button btnSpin = findViewById(R.id.btn_spin);
        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp.clear();
                tvResult.setText("");

                startWithDeley(0,rv1);
                startWithDeley(200,rv2);

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

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv);

    }

    private int getCurrentItem(RecyclerView rv) {
        return ((LinearLayoutManager) rv.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

    @Override
    public void getScrollResult(List<String> result,int position) {
        tvResult.setText("");
        temp = result;

        for (int i = 0; i < result.size() - 1; i++) {
            if (i > result.size() - 5) {
                tvResult.setText(tvResult.getText() + " \n " + temp.get(i));
            }
        }
    }


}

