package com.appdev.slots2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView tvResult;
    private SmoothScrollLM lm;
    private List<Integer> stateList = new ArrayList<>();
    private List<String>
            resultArr1 = new ArrayList<>(),
            resultArr2 = new ArrayList<>(),
            resultArr3 = new ArrayList<>();

    private List<String> tempArr = new ArrayList<>();

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
        adapter = new SlotsRVAdapter(this,data,this,rv.getId());
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        rv.smoothScrollToPosition(2);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv);

        addListener(rv);

    }

    private int getCurrentItem(RecyclerView rv) {
        return ((LinearLayoutManager) rv.getLayoutManager())
                .findFirstVisibleItemPosition();
    }


    //-Получение результата

    @Override
    public void getScrollResult1(List<String> result,int position) {
        resultArr1 = result;
    }

    @Override
    public void getScrollResult2(List<String> result,int position) {
        resultArr2 = result;
    }

    @Override
    public void getScrollResult3(List<String> result,int position) {
        resultArr3 = result;
    }

    //-Дожидаемся остановки скроллинга

    private void addListener(RecyclerView rv) {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,int newState) {
                super.onScrollStateChanged(recyclerView,newState);

                if (newState == 0) {
                    stateList.add(newState);
                    if (stateList.size() == 6) {
                        workWithResult();
                    }
                }

            }
        });

    }

    //-Обработка ркзультата

    private void workWithResult() {

        finalCut(resultArr1);
        finalCut(resultArr2);
        finalCut(resultArr3);

        tvResult.setText("ряд1: "+
                resultArr1.get(0)+" - "+
                resultArr1.get(1)+" - "+
                resultArr1.get(2)+"\n"+
                "ряд2: "+
                resultArr2.get(0)+" - "+
                resultArr2.get(1)+" - "+
                resultArr2.get(2)+"\n"+
                "ряд2: "+
                resultArr3.get(0)+" - "+
                resultArr3.get(1)+" - "+
                resultArr3.get(2)
        );

    }

    private void finalCut(List<String> resultArr){
        tempArr.addAll(resultArr);
        resultArr.clear();
        resultArr.add(0,tempArr.get(tempArr.size() - 3));
        resultArr.add(1,tempArr.get(tempArr.size() - 4));
        resultArr.add(2,tempArr.get(tempArr.size() - 5));
        tempArr.clear();
    }


}


